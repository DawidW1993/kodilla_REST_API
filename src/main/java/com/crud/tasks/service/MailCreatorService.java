package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {
    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;



    public String buildTrelloCardEmail(String message) {
        String COMPANY_DETAILS = adminConfig.getCompanyName() + ": " +
                adminConfig.getCompanyGoal() + "  " +
                adminConfig.getAdminMail() + "  " +
                adminConfig.getCompanyPhone();

        String GOODBYE_MESSAGE = "See You soon, " + adminConfig.getApplicationName() + " " + adminConfig.getApplicationVersion();

        Context context = new Context();
        context.setVariable("message",message);
        context.setVariable("task_url","http://localhost:8888/new_task_frontend/");
        context.setVariable("button","Visit website");
        context.setVariable("admin_name",adminConfig.getAdminName());
        context.setVariable("company_details",COMPANY_DETAILS);
        context.setVariable("goodbye_message",GOODBYE_MESSAGE);
        return templateEngine.process("mail/created-trello-card-mail.html",context);
    }

}
