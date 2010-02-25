#!/bin/sh
# last modified:22.04.2009 CTpaHHoe

[ -z "$PIDFILE" ] && exit 201
[ -z "$LOGFILE" ] && exit 202
[ -z "$RUN_HOME" ] && exit 203

[ -n "$JAVA_HOME" ] && export JAVA_HOME
[ -n "$JAVA_OPTS" ] && export JAVA_OPTS
[ -n "$RED5_HOME" ]  && export RED5_HOME

touch "$PIDFILE" || exit 204
# "$RUN_HOME/red5.sh" 2>&1 1>"$LOGFILE" >&2 &

cd $RED5_HOME
"$RUN_HOME/red5.sh" >>"$LOGFILE" 2>&1  &

RETVAL=$?
echo $! 2>/dev/null 1>"$PIDFILE"

exit $RETVAL

