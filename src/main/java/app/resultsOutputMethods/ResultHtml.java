package app.resultsOutputMethods;

import java.io.IOException;
import java.io.OutputStream;

import utils.AWorker;
import utils.Project;
import app.repository.InMemoryProjectRepo;
import app.repository.InMemoryWorkerRepo;
import app.repository.ProjectRepository;

public class ResultHtml extends  ResultOutputMethodToStream{

	public ResultHtml(OutputStream out) 
	{
		super(out);
	}

	@Override
	protected byte[] internalGiveResults(Object[] results) 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("<html>");
		for (int i = 0; i < results.length; i++)
		{
			String[] lines = results[i].toString().split("\n");
			
			for (int j = 0; j < lines.length; j++)
			{
				String line = lines[j];
				
				if(j == 0  && lines.length > 1)
				{
					builder.append("<h" + (i + 1) + ">").append(line).append("</h" + (i + 1) + ">");
				}
				builder.append("<p>").append(countSpaces(line)).append(line).append("</p>");
			}
			builder.append("<hr>");
		}
		builder.append("</html>");
		
		return builder.toString().getBytes();
	}
	
	private Object countSpaces(String line) {
		if(line.contains("    "))
			return fiveSpaces() + countSpaces(line.substring(4));
		else
			return "";
	}

	private static String fiveSpaces()
	{
		return "&nbsp&nbsp&nbsp&nbsp&nbsp";
	}
	
	public static void main(String[] args) throws IOException
	{
		InMemoryProjectRepo prepo = new InMemoryProjectRepo();
		RepositoryConstructor2 constructor = new RepositoryConstructor2();
		prepo = constructor.constructProjectRepository();
		
		InMemoryWorkerRepo wrepo = new InMemoryWorkerRepo();
		wrepo = constructor.constructWorkerRepo();
		
		prepo.getProjectById(3).addWorker(wrepo.getConsultantByID(1));
		prepo.getProjectById(3).addWorker(wrepo.getConsultantByID(2));
		prepo.getProjectById(3).addWorker(wrepo.getConsultantByID(3));
		prepo.getProjectById(3).setManager(wrepo.getManagerByID(5));
		prepo.getProjectById(4).addWorker(wrepo.getAWorkerByID(4));
		
		System.out.write(new ResultHtml(System.out).internalGiveResults(new Project[]{prepo.getProjectById(1), 
				prepo.getProjectById(2), prepo.getProjectById(3), prepo.getProjectById(4), prepo.getProjectById(5)}));
		System.out.write(new ResultHtml(System.out).internalGiveResults(new AWorker[]{wrepo.getAWorkerByID(1), 
				wrepo.getAWorkerByID(2), wrepo.getAWorkerByID(3), wrepo.getAWorkerByID(4), wrepo.getAWorkerByID(5)}));
//		System.out.write(new ResultHtml(System.out).internalGiveResults(new ProjectRepository[]{prepo}));
	}
}
