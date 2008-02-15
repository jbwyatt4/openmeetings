#!/bin/sh 
# 
# QoS4OpenMeetings
#
# Based on SipShaper v0.01 : http://www.voip-info.org/wiki/view/QoS+Linux+with+HFSC
# VoIP Traffic Shaper for 1024/128Kbit/s ADSL Line 
# 
# Written by Udo Schacht-Wiegand (2005-04-03)  
# 
# based on MYSHAPER from Dan Singletary (8/7/02) 
# http://www.nslu2-linux.org/wiki/HowTo/EnableTrafficShaping 
# and on phone-man's script 
# http://www.ip-phone-forum.de/forum/viewtopic.php?p=100371#100371 
# and on the (German) Linux Magazine 02/05 article p.28 
#   

# Set your outgoing interface and upload rate (in kbit/s) here 
DEV=eth1
RATEUP=128 
  
# Which servers should be prioritized (the server where openmeetings is installed) 
VOIPIPS="141.47.55.232"
 
# Which ports should be prioritized (keep this empty, it's only useful to improve a specific protocol)
VOIPPORTS="" # http sip iax ssh
  
# end of configuration options 
 
###################### 
# show status and exit 
###################### 
 
if [ "$1" = "status" ] 
then 
        echo "[qdisc]" 
        tc -s qdisc show dev $DEV 
 
        echo "" 
        echo "[class]" 
        tc -s class show dev $DEV 
 
        echo "" 
        echo "[filter]" 
        tc -s filter show dev $DEV 
 
        echo "" 
        echo "[iptables]" 
        iptables -t mangle -L SIPSHAPER -v -x 2> /dev/null 
        exit 
fi 
 
###################### 
# default: start it  # 
###################### 
 
# Reset everything to a known state (cleared) 
tc qdisc del dev $DEV root    2> /dev/null > /dev/null 
 
# Flush and delete tables 
iptables -t mangle --delete POSTROUTING -o $DEV -j SIPSHAPER 2> /dev/null > /dev/null 
iptables -t mangle --flush        SIPSHAPER 2> /dev/null > /dev/null 
iptables -t mangle --delete-chain SIPSHAPER 2> /dev/null > /dev/null 
 
###################### 
# stop it and exit  
###################### 
 
if [ "$1" = "stop" ]  
then  
        echo "Shaping removed on $DEV." 
        exit 
fi 
 
###################### 
# set up shaping 
###################### 
 
# add HFSC root qdisc 
tc qdisc add dev $DEV root handle 1: hfsc default 10 
 
# add main rate limit class 
tc class add dev $DEV parent 1: classid 1:1 hfsc sc rate ${RATEUP}kbit ul rate ${RATEUP}kbit 
 
# keep it simple: two classes only 
tc class add dev $DEV parent 1:1 classid 1:10 hfsc sc umax 1500b dmax 53ms rate 40kbit ul rate ${RATEUP}kbit 
tc class add dev $DEV parent 1:1 classid 1:11 hfsc sc umax 1500b dmax 30ms rate 80kbit ul rate ${RATEUP}kbit 
 
# add SIPSHAPER chain to the mangle table in iptables  
iptables -t mangle --new-chain SIPSHAPER 
iptables -t mangle --insert POSTROUTING -o $DEV -j SIPSHAPER 
 
# Filter for voip packets 
tc filter add dev $DEV parent 1: prio 1 protocol ip handle 1 fw flowid 1:11  
 
 
# VoIP ports as defined above 
for port in $VOIPPORTS 
do 
        iptables -t mangle -A SIPSHAPER -p udp --sport $port -j MARK --set-mark 1  
        iptables -t mangle -A SIPSHAPER -p udp --dport $port -j MARK --set-mark 1  
done 
 
# VoIP IPs as defined above  
for ip in $VOIPIPS 
do 
        iptables -t mangle -A SIPSHAPER -p udp --src $ip -j MARK --set-mark 1  
        iptables -t mangle -A SIPSHAPER -p udp --dst $ip -j MARK --set-mark 1  
        iptables -t mangle -A SIPSHAPER -p tcp --src $ip -j MARK --set-mark 1  
        iptables -t mangle -A SIPSHAPER -p tcp --dst $ip -j MARK --set-mark 1  
done 
 
 
 
echo SipShaper started on $DEV with ${RATEUP}kbit/s upload rate. 
 
echo -n "QoS activated for ports: "  
for port in $VOIPPORTS 
do 
        echo -n " $port" 
done 
echo "." 
 
echo -n "QoS activated for ip#  : " 
for ip in $VOIPIPS 
do 
        echo -n " $ip" 
done 
echo "." 
 
#end