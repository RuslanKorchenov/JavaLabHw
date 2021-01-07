import annotations.HtmlForm;
import annotations.HtmlInput;
import com.google.auto.service.AutoService;
import models.Form;
import models.Input;
import services.HtmlGenerator;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"annotations.HtmlForm"})
public class HtmlProcessor extends AbstractProcessor {

    private final HtmlGenerator htmlGenerator = new HtmlGenerator();

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);
        for (Element element : annotatedElements) {
            HtmlForm annotation = element.getAnnotation(HtmlForm.class);

            List<Input> inputs = new ArrayList<>();
            for (Element enclosedElement : element.getEnclosedElements()) {
                HtmlInput htmlInput = enclosedElement.getAnnotation(HtmlInput.class);
                if (htmlInput != null) {
                    Input input = Input.builder()
                            .name(htmlInput.name())
                            .placeholder(htmlInput.placeholder())
                            .type(htmlInput.type())
                            .build();
                    inputs.add(input);
                }
            }
            Form form = Form.builder()
                    .action(annotation.action())
                    .method(annotation.method())
                    .inputs(inputs)
                    .build();
            String path = HtmlProcessor.class.getProtectionDomain().getCodeSource()
                    .getLocation().getPath().substring(1)
                    + element.getSimpleName().toString()
                    + ".html";
            htmlGenerator.createHtml(form, path);
        }
        return true;
    }

}
