package app.commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import commands.GetProjectWorkers;
import commands.GetSubproject;
import utils.AWorker;
import utils.Consultant;
import utils.Leader;
import utils.Local;
import utils.Project;
import utils.Team;
import app.AppElement;
import app.actionListeners.AppSwingWorker;
import app.authentication.Authentication;
import app.elements.Message;
import app.framesAndPanels.DeleteProjectFrame;
import app.framesAndPanels.GetSubprojectsFrame;
import app.framesAndPanels.GetWorkersInProjectFrame;
import app.framesAndPanels.PostProjectFrame;
import app.repositoryHolders.RepositoryHolder;

public class GetWorkersInProject extends BaseCommand
{

	private GetWorkersInProjectFrame frame;

	public GetWorkersInProject(JSplitPane pane, RepositoryHolder repositories,
			Authentication authentication)
	{
		super(pane, repositories, authentication);
	}

	@Override
	public void execute()
	{
		new GetWorkersInProjectFrame(repositories).setVisible(true);
	}

	public class GetWorkersInProjectWorker extends AppSwingWorker
	{

		String typeOfWorker;
		long pid;

		public GetWorkersInProjectWorker(JSplitPane pane, long pid,
				String typeOfWorker)
		{
			super(pane);
			this.pid = pid;
			this.typeOfWorker = typeOfWorker;
		}

		/**
		 * Get's the {@code AWorker}s from a {@code Project}. If type of Worker
		 * is a Manager, then this {@code Command} get's the Manager (
		 * {@link Leader}) from the {@code Project} ({@link Project#manager}),
		 * if is Consultant then get's all the {@link Consultant}s in the
		 * {@link Team} of the {@code Project} ({@link Project#team}).
		 * 
		 * @return A JPanel with the results of the operation.
		 * 
		 * @wbp.parser.entryPoint
		 */
		@Override
		protected JPanel doInBackground()
		{

			publish("Status: Searching for the Project with the Worker in the repository...");
			Project project = repositories.getProjectsRepo()
					.getProjectById(pid);
			if (project == null)
			{
				JPanel panel = new JPanel();
				panel.add(new JLabel("Project with PID " + pid
						+ " does not exist in the repository."));
				return panel;
			}

			publish("Status: Searching for the Worker(s) in the Project...");
			if (typeOfWorker.equalsIgnoreCase("Manager"))
			{
				return getManager(project);
			} else if (typeOfWorker.equalsIgnoreCase("Consultant"))
			{
				return getWorkers(project);
			} else
			{
				JPanel panel = new JPanel();
				panel.add(new JLabel("Unrecognized type of Worker."));
				return panel;
			}

		}

		/**
		 * Gets the {@code Consultant}s working on the
		 * {@code Project}, if any have been assigned.
		 * 
		 * @param project The Project where the Workers should be searched.
		 * @return  A JPanel with the results of the operation.
		 */
		private JPanel getWorkers(Project project)
		{
			JPanel panel = new JPanel();
			Collection<AWorker> workers = project.getTeam();
			if (workers.isEmpty())
			{
				panel.add(new JLabel("No Workers found in Project."));
				return panel;
			}
			StringBuilder workersString = new StringBuilder();
			for (AWorker worker : workers)
			{
				workersString.append(worker.toString()).append("/n");
			}
			panel.add(new JLabel(workersString.toString()));
			return panel;
		}

		/**
		 * Returns the {@code Manager} of the {@code Project}, if one has been
		 * assigned.
		 * 
		 * @param project The Project where the Workers should be searched.
		 * @return  A JPanel with the results of the operation.
		 */
		private JPanel getManager(Project project)
		{
			JPanel panel = new JPanel();
			Leader manager = project.getManager();
			if (manager == null)
			{
				panel.add(new JLabel("No Manager found in Project."));
				return panel;
			}
			panel.add(new JLabel(manager.toString()));
			return panel;
		}

	}

	public class OkActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			new GetWorkersInProjectWorker(pane, pid, typeOfWorker).execute();
			frame.dispose();
		}
	}
}