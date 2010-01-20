var masterUpdateTimer = setInterval( updateMasterStatus, 1000 );
var masterStatus = false;
var my_id = uuid4String();

var om_onstatuschange_last = '%E%';
var om_onmakecall_last = '%E%';
var om_onincomingcall_last = '%E%';
var om_oncallanswered_last = '%E%';
var om_oncallrejected_last = '%E%';
var om_onstatusmessage_last = '%E%';
var om_callstate = '%E%';

var sv_s_addr;
var sv_o_link;
var sv_id = null;
var sv_displayname = '';

function master_init()
{
	om_setinstanceid(my_id);

	jaaulde.utils.cookies.set('onstatuschange','%E%');
	jaaulde.utils.cookies.set('onmakecall','%E%');
	jaaulde.utils.cookies.set('onincomingcall','%E%');
	jaaulde.utils.cookies.set('oncallanswered','%E%');
	jaaulde.utils.cookies.set('oncallrejected','%E%');
	jaaulde.utils.cookies.set('onstatusmessage','%E%');
	jaaulde.utils.cookies.set('messageQueue','%E%');
	jaaulde.utils.cookies.set('callstate','N');
}


function utcNow()
{
	var currentDate = new Date();
	return Date.UTC(currentDate.getFullYear(),
						currentDate.getMonth(),
						currentDate.getDay(),
						currentDate.getHours(),
						currentDate.getMinutes(),
						currentDate.getSeconds(),
						currentDate.getMilliseconds());
}

function updateMasterStatus()
{
	var currentCookie = jaaulde.utils.cookies.get('master_id');
	var previousStatus = masterStatus;

	if ( currentCookie != null )
	{
		var args = currentCookie.split('|');
		
		if ( args[ 0 ] != my_id )
		{
			//We are slave?			
			
			var delta = utcNow() - parseInt(args[1]);
						
			if ( delta > 5000 )
			{
				//We are master since timeout has expired
				var data = my_id + '|' + utcNow();
				
				jaaulde.utils.cookies.del( 'master_id' );
				jaaulde.utils.cookies.set( 'master_id', data );			
				
				masterStatus = true;

				om_setserveraddress( sv_s_addr );
				om_setomlink( sv_o_link );								
			}
			else
			{
				//Timeout is ok, we are a slave
				masterStatus = false;
			}
		}
		else
		{
			//We are master, refreshing time
			var data = my_id + '|' + utcNow()
			
			jaaulde.utils.cookies.del( 'master_id' );
			jaaulde.utils.cookies.set( 'master_id', data );			
			masterStatus = true;
		}
	}
	else
	{
		//We are master, setting time
		var data = my_id + '|' + utcNow();
		
		masterStatus = true;
		jaaulde.utils.cookies.set( 'master_id', data );

		om_setserveraddress( sv_s_addr );
		om_setomlink( sv_o_link );
	}	

	if ( previousStatus != masterStatus )
	{
		my_masterstatuschanged( masterStatus );

		if ( masterStatus )
		{
			master_init();

			//TODO: implement what should be done when we are logged in but the master window is closed
			if ( sv_id != null )
			{
				om_login(sv_id, sv_displayname);
			}
		}
	}

	if ( masterStatus )
	{
		parseMessageQueue();
	}
	
	updateParameters();
}

function postMessageToQueue( methodName, arg0, arg1 )
{
	var messageQueue = jaaulde.utils.cookies.get('messageQueue');
	jaaulde.utils.cookies.del('messageQueue');
	
	if ( messageQueue != '%E%' )
	{
		messageQueue = methodName + '!#!' + arg0 + '!#!' + arg1 + '~#~' + messageQueue;
	}
	else
	{
		messageQueue = methodName + '!#!' + arg0 + '!#!' + arg1 + '~#~';
	}

	jaaulde.utils.cookies.set('messageQueue', messageQueue);
}

function sv_setuserinfo(id, name)
{
	sv_id = id;
	sv_displayname = name;
}

function sv_setserveraddress(address)
{
	sv_s_addr = address;
}

function sv_setomlink(link)
{
	sv_o_link = link;
}

function sv_login(id, name)
{
	postMessageToQueue('login',id, name);
}

function sv_logoff()
{
	postMessageToQueue('logoff');
}

function sv_makecall(callee)
{
	postMessageToQueue('makecall',callee);
}

function sv_answercall()
{
	postMessageToQueue('answer');
}

function sv_rejectcall(caller)
{
	postMessageToQueue('reject');
}

function sv_clearoutgoingcall()
{
	postMessageToQueue('clearout');
}

function sv_clearincomingcall()
{
	postMessageToQueue('clearin');
}

function parseMessageQueue()
{
	var messageQueueString = jaaulde.utils.cookies.get('messageQueue');
	jaaulde.utils.cookies.set('messageQueue','%E%');

	if ( messageQueueString != null )
	{
		if ( messageQueueString != '%E%')
		{
			var messages = messageQueueString.split('~#~');
			for ( var i = 0; i < messages.length; i++ )
			{
				if ( messages[i] != '' )
				{
					var elements = messages[i].split('!#!');
				
					switch ( elements[0] )
					{
						case 'login':
						{
							om_login( elements[1], elements[2] );

							break;
						}
						case 'makecall':
						{
							om_makecall( elements[1] );
						
							break;
						}
						case 'answer':
						{
							om_answercall();

							break;
						}
						case 'reject':
						{
							om_rejectCall();
	
							break;
						}
						case 'clearout':
						{
							om_clearoutgoingcall();

							jaaulde.utils.cookies.set('oncallanswered','%E%');

							jaaulde.utils.cookies.set('oncallrejected','%E%');

							break;
						}
						case 'clearin':
						{
							//om_clearincomingcall();

							jaaulde.utils.cookies.set('onincomingcall','%E%');

							break;
						}
						case 'logoff':
						{
							om_logoff();

							break;
						}
						default: 
						{
							alert("parseMessageQueue - message "+messageQueueString + " unparsable, element: " + elements[0] );
						
							break;
						}
					}
				}
			}
		}
	}
}

function om_onstatuschange(status)
{
	if ( status )
	{
		jaaulde.utils.cookies.set('onstatuschange','ON');
	}
	else
	{
		jaaulde.utils.cookies.set('onstatuschange','OFF');
	}
}

function om_onmakecall(result)
{
	if ( status )
	{
		jaaulde.utils.cookies.set('onmakecall','OK');
	}
	else
	{
		jaaulde.utils.cookies.set('onmakecall','FL');
	}
}

function om_onincomingcall(caller, link)
{
	jaaulde.utils.cookies.set('onincomingcall',caller+'!#!'+link);
}

function om_oncallanswered( callee, link )
{
	jaaulde.utils.cookies.set('oncallanswered',callee+'!#!'+link);
}

function om_oncallrejected( callee )
{
	jaaulde.utils.cookies.set('oncallrejected',callee);
}

function om_onincomingcallstatechanged( state )
{
	if ( state )
	{
		jaaulde.utils.cookies.set('callstate','C');
	}
	else
	{
		jaaulde.utils.cookies.set('callstate','N');
	}
}

function om_onstatusmessage( message )
{
	var oldMessage = jaaulde.utils.cookies.get('onstatusmessage');
	if ( oldMessage != '%E%' )
	{
		jaaulde.utils.cookies.set('onstatusmessage',oldMessage+message);
	}
	else
	{
		jaaulde.utils.cookies.set('onstatusmessage',message);
	}
}

function updateParameters()
{
	var temp = jaaulde.utils.cookies.get('onstatusmessage');
	if ( temp != om_onstatusmessage_last )
	{
		my_onstatusmessage( temp );
		om_onstatusmessage_last = '%E%';
		jaaulde.utils.cookies.set('onstatusmessage','%E%');
	}
	temp = jaaulde.utils.cookies.get('oncallrejected');
	if ( temp != om_oncallrejected_last )
	{
		if ( temp != '%E%' )
		{
			my_oncallrejected( temp );
			om_oncallrejected_last = temp;
		}
		else
		{
			my_clearrejected();
		}
		
		om_oncallrejected_last = temp;
	}
	temp = jaaulde.utils.cookies.get('oncallanswered');
	if ( temp != om_oncallanswered_last )
	{
		if ( temp != '%E%' )
		{
			var params = temp.split('!#!');
			my_oncallanswered( params[0], params[1] );			
		}
		else
		{
			my_clearanswered();
		}

		om_oncallanswered_last = temp;
	}
	temp = jaaulde.utils.cookies.get('onincomingcall');
	if ( temp != om_onincomingcall_last )
	{
		if ( temp != '%E%' )
		{
			var params = temp.split('!#!');
			my_onincomingcall( params[0], params[1] );
		}
		else
		{
			my_clearincoming();
		}
		om_onincomingcall_last = temp;
	}
	temp = jaaulde.utils.cookies.get('onmakecall');
	if ( temp != om_onmakecall_last )
	{
		if ( temp = "OK" )
		{
			my_onmakecall( true );
		}
		else
		{
			my_onmakecall( false );
		}
		om_onmakecall_last = temp;
	}
	temp = jaaulde.utils.cookies.get('onstatuschange');
	if ( temp != om_onstatuschange_last )
	{
		if ( temp == 'ON' )
		{
			my_onstatuschange( true );
		}
		else
		{
			my_onstatuschange( false );
		}
		om_onstatuschange_last = temp;
	}
	temp = jaaulde.utils.cookies.get('callstate');
	if ( temp != om_callstate )
	{
		if ( temp == 'N' )
		{
			my_clearincoming();
		}
		om_callstate = temp;
	}
}