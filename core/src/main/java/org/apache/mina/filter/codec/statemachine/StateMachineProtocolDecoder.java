/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.apache.mina.filter.codec.statemachine;

import org.apache.mina.common.IoBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * 
 * @author The Apache MINA Project (dev@mina.apache.org)
 * @version $Rev$, $Date$
 */
public class StateMachineProtocolDecoder implements ProtocolDecoder {
    private final DecodingStateMachine stateMachine;

    public StateMachineProtocolDecoder(DecodingStateMachine stateMachine) {
        if (stateMachine == null) {
            throw new NullPointerException("stateMachine");
        }
        this.stateMachine = stateMachine;
    }
    
    /**
     * Returns the {@link DecodingStateMachine} employed by this decoder.
     */
    protected DecodingStateMachine getStateMachine() {
        return stateMachine;
    }

    public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
            throws Exception {
        stateMachine.decode(in, out);
    }

    public void finishDecode(IoSession session, ProtocolDecoderOutput out)
            throws Exception {
        stateMachine.finishDecode(out);
    }

    public void dispose(IoSession session) throws Exception {}
}
