package org.openmeetings.server.socket;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.List;
import java.util.Map;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.openmeetings.server.beans.ServerFrameBean;
import org.openmeetings.server.beans.ServerSharingSessionBean;
import org.openmeetings.server.beans.ServerSharingViewerBean;
import org.openmeetings.server.beans.ServerStatusBean;
import org.openmeetings.server.beans.ServerViewerRegisterBean;
import org.openmeetings.server.cache.ServerSharingSessionList;
import org.openmeetings.server.cache.ServerSharingViewersList;
import org.openmeetings.server.codec.ServerDesktopCodecSharingFactory;
import org.red5.logging.Red5LoggerFactory;
import org.slf4j.Logger;

/**
 * @author sebastianwagner
 *
 */
public class ServerSocketMinaProcess {
	
	private static final Logger log = Red5LoggerFactory.getLogger(ServerSocketMinaProcess.class, "openmeetings");
	
	private static NioSocketAcceptor acceptor = null;
	
	public static final int port = 4445;
	
	
	public static void sendMessageToSession(ServerFrameBean serverFrameBean) {
		try {
			
			List<ServerSharingViewerBean> viewersList = ServerSharingViewersList.getViewersByPublicSID(serverFrameBean.getPublicSID());
			
			if (viewersList == null) {
				//No Viewers registered via ODSP-Connection
				return;
			}
			
			for (ServerSharingViewerBean serverSharingViewerBean : viewersList) {
				
				IoSession session = ServerSocketMinaProcess.getSessionById(serverSharingViewerBean.getSessionId());
				
				
				session.write(serverFrameBean);
			}
			
		} catch (Exception err) {
			log.error("[sendMessageToSession]",err);
		}
	}
	
	public static IoSession getSessionById(Long sessionId) {
		try {
			
			if (acceptor == null) {
				throw new Exception("acceptor is Null");
			}
			
			if (acceptor.getManagedSessions().containsKey(sessionId)) {
				return acceptor.getManagedSessions().get(sessionId);
			} else {
				throw new Exception("Could Not Find IoSession for id: "+sessionId);
			}
			
			
			
		} catch (Exception err) {
			log.error("[getSession]",err);
		}
		return null;
	}
	
	public void doInitSocket() {
		try {
			
			acceptor = new NioSocketAcceptor();
			
			acceptor.getFilterChain().addLast("protocol", new ProtocolCodecFilter(new ServerDesktopCodecSharingFactory()));
			
//				acceptor.getSessionConfig().setMinReadBufferSize(49152);
//				acceptor.getSessionConfig().setMaxReadBufferSize(65535);
//				acceptor.getSessionConfig().setReadBufferSize(49152);
//				acceptor.getSessionConfig().setReceiveBufferSize(65535);
			
			acceptor.setHandler(new ServerPacketMinaHandler(this));
			
			DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();

	        chain.addLast("logger", new LoggingFilter());


//		    DatagramSessionConfig dcfg = acceptor.getSessionConfig();
//	
//		    dcfg.setReuseAddress(true);

	        acceptor.bind(new InetSocketAddress(port));

	        log.debug("TCPServer listening on port " + port);
	        //log.debug("UDPServer listening on port " + i);
	        
			
		} catch (Exception err) {
			log.debug("[doInitSocket]",err);
		}
	}
	
	



	/**
	 * @param remoteAddress
	 * @param long1
	 */
//	public void recvUpdate(SocketAddress remoteAddress, byte[] data) {
//		// TODO Auto-generated method stub
//		
//		ServerPacketHandler serverPacketHandler = new ServerPacketHandler();
//        
//        serverPacketHandler.handleIncomingByte(data);
//	}
	
	public void recvServerStatusBean(SocketAddress remoteAddress, Long sessionId, ServerStatusBean serverStatusBean) {
		log.debug("sequenceNumber STATUS "+serverStatusBean.getSequenceNumber());
		
		//log.debug("handleIncomingByte -- Status Bean ");
		if (serverStatusBean.getMode() == 0) {
			ServerSharingSessionList.startSession(sessionId,serverStatusBean);
		} else if (serverStatusBean.getMode() == 4) {
			log.debug("STOP SESSION");
			ServerSharingSessionList.stopSession(sessionId);
		}
		
	}

	public void recvServerFrameBean(SocketAddress remoteAddress, ServerFrameBean serverFrameBean) {

		
//		log.debug("mode "+serverFrameBean.getMode());
		log.debug("recvServerFrameBean sequenceNumber "+serverFrameBean.getSequenceNumber());
//		log.debug("lengthSecurityToken "+serverFrameBean.getLengthSecurityToken());
//		log.debug("xValue "+serverFrameBean.getXValue());
//		log.debug("yValue "+serverFrameBean.getYValue());
//		log.debug("width "+serverFrameBean.getWidth());
//		log.debug("height "+serverFrameBean.getHeight());
//		log.debug("lengthPayload "+serverFrameBean.getLengthPayload());
		
		//log.debug("handleIncomingByte -- Frame Bean ");

		ServerSharingSessionList.addFrameToSession(serverFrameBean);

	}

	/**
	 * @param remoteAddress
	 */
	public void removeClient(SocketAddress remoteAddress, Long sessionId) {
		// TODO Auto-generated method stub
		
		ServerSharingSessionList.removeSession(sessionId);
		ServerSharingViewersList.removeSession(sessionId);
	}



	/**
	 * @param remoteAddress
	 */
	public void addClient(SocketAddress remoteAddress) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param remoteAddress
	 * @param message
	 */
	public void recvServerViewerRegisterBean(SocketAddress remoteAddress, Long sessionId, 
			ServerViewerRegisterBean message) {
		
		ServerSharingViewersList.addNewViewerClient(sessionId,message);
		
		//Initial Screens loading
		ServerSharingSessionBean serverSharingSessionBean = ServerSharingSessionList.getServerSharingSessionBeanByPublicSID(message.getPublicSID());
		
		IoSession session = ServerSocketMinaProcess.getSessionById(sessionId);
		
		for (ServerFrameBean serverFrameBean : serverSharingSessionBean.getServerFrameBeans()) {
			session.write(serverFrameBean);
		}
		
	}
	

}
