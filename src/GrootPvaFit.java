import org.phoebus.pv.PV;
import org.phoebus.pv.PVPool;
import org.phoebus.core.vtypes.VTypeHelper;
import org.epics.vtype.Time;

import java.util.concurrent.CountDownLatch;
import java.time.Instant;

import org.jlab.groot.data.*;
import org.jlab.groot.ui.*;
import org.jlab.groot.math.*;
import org.jlab.groot.fitter.DataFitter;

public class GrootPvaFit
{
    public static void main(String[] args) throws Exception
    {
        final PV pv = PVPool.getPV("pva://demo:circle:x");
	GraphErrors gr = new GraphErrors();
	gr.setTitle("Example Graph"); 
	gr.setTitleX("Time");
	gr.setTitleY("demo:circle:x");

	F1D func = new F1D("func","[amp]*gaus(x,[mean],[sigma])", 0.0, 20000.0);
	func.setParameter(0, 1.0);  
	func.setParameter(1, 1.0);  
	func.setParameter(2, 0.0);  
	DataFitter fitter = new DataFitter();
	//fitter.fit(func, gr, "Q");		
	func.setOptStat(111);
	
	TCanvas c1 = new TCanvas("c1", 800, 600);
	c1.draw(gr);
	//c1.draw(func,"same");				
	
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

		gr.addPoint(msec*1e-10, val, 0, 0);
		
		//c1.draw(gr);
		fitter.fit(func, gr, "Q");
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
