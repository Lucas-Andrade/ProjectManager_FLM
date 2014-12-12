package app.elements;

import java.util.Comparator;

import utils.AWorker;

public class WorkerComparator implements Comparator<AWorker>{

	@Override
	public int compare(AWorker w1, AWorker w2) 
	{
		long cid1;
		long cid2;
		
		try{
			cid1 = w1.getCID();
		}
		catch(NullPointerException e)
		{
			cid1 = -1;
		}
		
		try{
			cid2 = w2.getCID();
		}
		catch(NullPointerException e)
		{
			cid2 = -1;
		}
		
		return cid1 > cid2 ? 1 : (cid1 < cid2) ? -1 : 0;
	}

}
