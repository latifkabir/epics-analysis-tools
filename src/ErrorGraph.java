/**
 * @author Latif Kabir < jlab.org/~latif >
 *
 */

import org.jlab.groot.data.*;
import org.jlab.groot.ui.*;

class ErrorGraph
{
    public static void main(String[] args)
    {	
	GraphErrors gr = new GraphErrors();
	TCanvas c1 = new TCanvas("c1", 800, 600);

	for(int i = 0; i < 20; i++)
	{
	    gr.addPoint( i, i*i, 0, 0);
	}
	gr.setLineColor(4);
	gr.setLineThickness(4);
	c1.draw(gr);	
    }
}
