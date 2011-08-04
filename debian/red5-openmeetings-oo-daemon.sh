#!/bin/sh

# last modified:22.04.2009 CTpaHHoe

[ -z "$PIDFILE" ] && exit 201
[ -z "$LOGFILE" ] && exit 202
[ -z "$SOFFICE" ] && exit 203
[ -z "$SOFFICE_ARGS" ] && exit 204

touch "$PIDFILE" || exit 205

# echo $SOFFICE $SOFFICE_ARGS 
$SOFFICE $SOFFICE_ARGS  >>"$LOGFILE" 2>&1  &

RETVAL=$?
echo $! 2>/dev/null 1>"$PIDFILE"

exit $RETVAL

