package stories.it;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.LoadFromRelativeFile;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

/**
 * Created by Sicness on 17.02.2015.
 */
public class PipelineScenarios extends JUnitStories {
    @Override
    public Configuration configuration() {
        Class<? extends Embeddable> embeddableClass = this.getClass();
        Configuration configuration = super.configuration();
        configuration.useStoryLoader(new LoadFromClasspath(this.getClass()));

        StoryReporterBuilder builder = new StoryReporterBuilder();
        Format[] formats = new Format[]{Format.CONSOLE, Format.HTML,
                Format.XML, Format.STATS, Format.HTML_TEMPLATE};
        builder.withFormats(formats).withCodeLocation(codeLocationFromClass(getClass()));

        StoryControls storyControls = new StoryControls();
        storyControls.doIgnoreMetaFiltersIfGivenStory(true);

        return configuration.useStoryReporterBuilder(builder)
                .usePendingStepStrategy(new FailingUponPendingStep())
                .useStoryControls(storyControls);
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

    @Override
    protected List<String> storyPaths() {
        StoryFinder finder = new StoryFinder();
        String searchBase = codeLocationFromClass(getClass()).getFile();
        return finder.findPaths(searchBase, Arrays.asList(getStoriesGlob()), null);
    }
}
