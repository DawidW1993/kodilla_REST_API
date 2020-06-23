package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.persistence.metamodel.ListAttribute;
import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {
    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    GetMailTasksList getMailTasksList;

    public String buildTrelloEverydayEmail(String message){
        List<String> tasksList = getMailTasksList.get();

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
        context.setVariable("show_button",false);
        context.setVariable("is_friend",true);
        context.setVariable("admin_config",adminConfig);
        context.setVariable("database_tasks",tasksList);
        return templateEngine.process("mail/created-tasks-everyday-mail.html", context);
    }

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending Tasks to Trello");

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
        context.setVariable("show_button",false);
        context.setVariable("is_friend",true);
        context.setVariable("admin_config",adminConfig);
        context.setVariable("application_functionality",functionality);
        return templateEngine.process("mail/created-trello-card-mail.html",context);
    }

}
