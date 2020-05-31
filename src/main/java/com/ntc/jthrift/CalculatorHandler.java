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
import com.ntc.thrift.tutorial.InvalidOperation;
import com.ntc.thrift.tutorial.SharedStruct;
import com.ntc.thrift.tutorial.Work;
import java.util.*;
import org.apache.thrift.TException;

/**
 *
 * @author nghiatc
 * @since May 31, 2020
 */
public class CalculatorHandler implements Calculator.Iface {
    private HashMap<Integer, SharedStruct> log = new HashMap<>();

    public CalculatorHandler() {}

    @Override
    public void ping() throws TException {
        System.out.println("ping()");
    }

    @Override
    public int add(int num1, int num2) throws TException {
        System.out.println("add(" + num1 + "," + num2 + ")");
        return num1 + num2;
    }

    @Override
    public int calculate(int logid, Work work) throws InvalidOperation, TException {
        System.out.println("calculate(" + logid + ", {" + work.op + "," + work.num1 + "," + work.num2 + "})");
        int val = 0;
        if (work != null) {
            switch (work.op) {
                case ADD:
                    val = work.num1 + work.num2;
                    break;
                case SUBTRACT:
                    val = work.num1 - work.num2;
                    break;
                case MULTIPLY:
                    val = work.num1 * work.num2;
                    break;
                case DIVIDE:
                    if (work.num2 == 0) {
                        InvalidOperation io = new InvalidOperation();
                        io.whatOp = work.op.getValue();
                        io.why = "Cannot divide by 0";
                        throw io;
                    }
                    val = work.num1 / work.num2;
                    break;
                default:
                    InvalidOperation io = new InvalidOperation();
                    io.whatOp = work.op.getValue();
                    io.why = "Unknown operation";
                    throw io;
            }
        }

        SharedStruct entry = new SharedStruct();
        entry.key = logid;
        entry.value = Integer.toString(val);
        log.put(logid, entry);

        return val;
    }

    @Override
    public void zip() throws TException {
        System.out.println("zip()");
    }

    @Override
    public SharedStruct getStruct(int key) throws TException {
        System.out.println("getStruct(" + key + ")");
        return log.get(key);
    }

}
