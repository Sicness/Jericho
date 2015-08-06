package stories.it;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromRelativeFile;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

/**
 * Created by Sicness on 17.02.2015.
 */
public class PipelineScenarios extends JUnitStory {
    @Override
    public Configuration configuration() {
        URL storyURL = null;
        try {
            String searchBase = codeLocationFromClass(getClass()).getFile();
            // This requires you to start Maven from the project directory
            storyURL = new URL("file://" + System.getProperty("user.dir"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return new MostUsefulConfiguration().useStoryLoader(
                new LoadFromRelativeFile(storyURL)).useStoryReporterBuilder(
                new StoryReporterBuilder().withFormats(StoryReporterBuilder.Format.HTML,
                        StoryReporterBuilder.Format.STATS).withFailureTrace(true));
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context_test.xml");
        return new SpringStepsFactory(configuration(), context);
    }

    private String getStoriesGlob() {

        return new File(new File("**"), getStoryFilter()).getPath();
    }

    private String getStoryFilter() {

        String storyFilter = System.getProperty("storyFilter", "*");
        return String.format("%s.story", storyFilter);
    }

    @Override
    @Test
    public void run() {
        try {
            super.run();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
