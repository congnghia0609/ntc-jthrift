/*
 * Copyright 2020 nghiatc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ntc.jthrift;

import com.ntc.thrift.tutorial.Calculator;
import java.net.InetAddress;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

/**
 *
 * @author nghiatc
 * @since May 31, 2020
 */
public class Server {
    
    public CalculatorHandler handler = new CalculatorHandler();
    public Calculator.Processor processor;
    private Thread thread;
    
    public void start() {
        try {
            processor = new Calculator.Processor(handler);
            
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int port = 9090;
                        InetAddress inetAddr = InetAddress.getByName("localhost");
                        TNonblockingServerSocket socket = new TNonblockingServerSocket(port);
                        
                        TThreadedSelectorServer.Args options = new TThreadedSelectorServer.Args(socket);
                        options.protocolFactory(new TBinaryProtocol.Factory(false /*strictRead*/, true /*strictWrite*/));
                        options.transportFactory(new TFramedTransport.Factory());
                        options.processor(processor);
                        
                        TServer tserver = new TThreadedSelectorServer(options);
                        System.out.println("Server is starting on: " + port);
                        tserver.serve();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
