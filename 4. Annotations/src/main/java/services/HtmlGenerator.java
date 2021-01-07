package services;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import models.Form;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HtmlGenerator {

    private Configuration cfg;

    public HtmlGenerator() {
        cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setClassForTemplateLoading(this.getClass(), "/");
        cfg.setDefaultEncoding("UTF-8");
    }

    public void createHtml(Form form, String path) {
        Map<String, Object> map = new HashMap<>();

        try {
            FileWriter writer = new FileWriter(new File(path));
            map.put("form", form);
            Template t = cfg.getTemplate("form.ftlh");
            t.process(map, writer);
        } catch (IOException | TemplateException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
