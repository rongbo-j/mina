package org.apache.mina.protocol.io;

import org.apache.mina.common.FilterChainType;
import org.apache.mina.protocol.AbstractProtocolHandlerFilterChain;
import org.apache.mina.protocol.ProtocolSession;
import org.apache.mina.protocol.io.IoAdapter.ProtocolSessionImpl;
import org.apache.mina.util.Queue;

class IoProtocolFilterChain extends AbstractProtocolHandlerFilterChain {

    IoProtocolFilterChain(FilterChainType type) {
        super(type);
    }

    protected void doWrite(ProtocolSession session, Object message) {
        ProtocolSessionImpl s = ( ProtocolSessionImpl ) session;
        Queue writeQueue = s.writeQueue;
        synchronized( writeQueue )
        {
            writeQueue.push( message );
        }

        s.adapter.doWrite( s.session );
    }
}
