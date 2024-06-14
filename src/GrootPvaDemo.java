import org.phoebus.pv.PV;
import org.phoebus.pv.PVPool;
import org.phoebus.core.vtypes.VTypeHelper;
import org.epics.vtype.Time;

import java.util.concurrent.CountDownLatch;
import java.time.Instant;

import org.jlab.groot.data.*;
import org.jlab.groot.ui.*;

public class GrootPvaDemo
{
    public static void main(String[] args) throws Exception
    {
        final PV pv = PVPool.getPV("pva://demo:circle:x");
	GraphErrors gr = new GraphErrors();
	gr.setTitle("Example Graph"); 
	gr.setTitleX("Time");
	gr.setTitleY("demo:circle:x");	
	TCanvas c1 = new TCanvas("c1", 800, 600);
	c1.draw(gr);
	
        try
        {
            // Await connection
            CountDownLatch connect = new CountDownLatch(3000);
            pv.onValueEvent().subscribe(value ->
            {
                // System.out.println(pv.getName() + " = " + value);
		final double val = VTypeHelper.toDouble(value);
		final Instant time = VTypeHelper.getTimestamp(value);
		double msec = time.toEpochMilli();
		
                // System.out.println(val + " at " + time);

		gr.addPoint(msec, val, 0, 0);
		//c1.draw(gr);
		c1.getCanvas().update();

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
