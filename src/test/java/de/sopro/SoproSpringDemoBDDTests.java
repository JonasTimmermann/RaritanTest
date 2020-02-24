package de.sopro;

import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;
import static org.jbehave.core.reporters.Format.TXT;
import static org.jbehave.core.reporters.Format.XML;

import java.text.SimpleDateFormat;
import java.util.List;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.model.TableTransformers;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.ParameterControls;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.ParameterConverters.DateConverter;
import org.jbehave.core.steps.ParameterConverters.ExamplesTableConverter;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 * {@link Embeddable} class to run multiple textual stories via JUnit using
 * Spring Dependency Injection to compose the steps classes.
 * </p>
 * <p>
 * Stories are specified in classpath and correspondingly the
 * {@link LoadFromClasspath} story loader is configured.
 * </p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SoproSpringDemoBDDTests extends JUnitStories {

	@Autowired
	private ApplicationContext applicationContext;

	public SoproSpringDemoBDDTests() {
		initConfiguration();
	}

	private void initConfiguration() {

		Class<? extends Embeddable> embeddableClass = this.getClass();
		LoadFromClasspath resourceLoader = new LoadFromClasspath(embeddableClass);
		TableTransformers tableTransformers = new TableTransformers();
		ParameterControls parameterControls = new ParameterControls();
		ParameterConverters parameterConverters = new ParameterConverters(resourceLoader, tableTransformers);
		ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(new LocalizedKeywords(), resourceLoader,
				parameterConverters, parameterControls, tableTransformers);
		parameterConverters.addConverters(new DateConverter(new SimpleDateFormat("yyyy-MM-dd")),
				new ExamplesTableConverter(examplesTableFactory));

		useConfiguration(new MostUsefulConfiguration()
				.useStoryLoader(new LoadFromClasspath(embeddableClass)).useStoryParser(new RegexStoryParser(examplesTableFactory))
				.useStoryReporterBuilder(new StoryReporterBuilder().withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass))
						.withDefaultFormats().withFormats(CONSOLE, TXT, HTML, XML)).useParameterConverters(parameterConverters)
				.useParameterControls(parameterControls).useTableTransformers(tableTransformers));
	}

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new SpringStepsFactory(configuration(), applicationContext);
	}

	protected List<String> storyPaths() {
		return new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(this.getClass()), "**/*.story", "**/excluded*.story");
	}

}
