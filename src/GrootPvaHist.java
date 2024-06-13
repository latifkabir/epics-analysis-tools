import org.phoebus.pv.PV;
import org.phoebus.pv.PVPool;
import org.phoebus.core.vtypes.VTypeHelper;
import org.epics.vtype.Time;

import java.util.concurrent.CountDownLatch;
import java.time.Instant;

import org.jlab.groot.data.*;
import org.jlab.groot.ui.*;

public class GrootPvaHist
{
    public static void main(String[] args) throws Exception
    {
        final PV pv = PVPool.getPV("pva://demo:circle:x");
	H1F histogram = new H1F("histogram", 300, -2, 2);
	TCanvas c1 = new TCanvas("c1", 800, 600);
	c1.draw(histogram);
	
        try
        {
            // Await connection
            CountDownLatch connect = new CountDownLatch(30);
            pv.onValueEvent().subscribe(value ->
            {
                // System.out.println(pv.getName() + " = " + value);
		final double val = VTypeHelper.toDouble(value);
		final Instant time = VTypeHelper.getTimestamp(value);
		double msec = time.toEpochMilli();
		
                // System.out.println(val + " at " + time);

		histogram.fill(val);
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
