/*******************************************************************************
 * Copyright (c) 2022 Oak Ridge National Laboratory.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/


import org.phoebus.pv.PV;
import org.phoebus.pv.PVPool;

import java.util.concurrent.CountDownLatch;

/** Write elements of data served by core-pva/src/test/java/org/epics/pva/server/BoolDemo.java
 *  @author Kay Kasemir
 */

public class PvaDemo
{
    public static void main(String[] args) throws Exception
    {
        final PV pv = PVPool.getPV("pva://demo:circle:x");

        try
        {
            // Await connection
            CountDownLatch connect = new CountDownLatch(10);
            pv.onValueEvent().subscribe(value ->
            {
                System.out.println(pv.getName() + " = " + value);
                if (!PV.isDisconnected(value))
                    connect.countDown();
            });
            connect.await();

            pv.asyncWrite(0.555555555).get();
        }
        finally
        {
            PVPool.releasePV(pv);
        }
    }
}
