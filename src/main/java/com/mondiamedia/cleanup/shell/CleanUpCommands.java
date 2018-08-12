package com.mondiamedia.cleanup.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import com.mondiamedia.cleanup.service.CleanUpService;
import com.mondiamedia.cleanup.service.CleanUpServiceReport;

@Component
public class CleanUpCommands implements CommandMarker {

    private boolean simpleCommandExecuted = false;

    @Autowired
    private CleanUpService cleanUpService;

    @Autowired
    private CleanUpServiceReport cleanUpServiceReport;

    @CliAvailabilityIndicator({ "hw simple" })
    public boolean isSimpleAvailable() {
        return true; // always available return true;
    }

    @CliAvailabilityIndicator({ "hw complex", "hw enum" })
    public boolean isComplexAvailable() {
        if (simpleCommandExecuted) {
            return true;
        } else {
            return false;
        }
    }

    @CliCommand(value = "cleanUp", help = "cleanUp All Duplicate Users From UserService, Subscription, wallet and Play List")
    public void listAllDuplicateUsersFromUserService(@CliOption(key = { "service" }, mandatory = false, help = "The name of service") final String service) {
        simpleCommandExecuted = true;
        cleanUpService.cleanUpDuplicateUser();
    }

    @CliCommand(value = "cleanUpReport", help = "get Report for All Duplicate Users Id From UserService")
    public void listReportForAllDuplicateUsersFromUserService(
            @CliOption(key = { "service" }, mandatory = false, help = "The name of service") final String service) {
        simpleCommandExecuted = true;
        cleanUpServiceReport.cleanUpDuplicateUser();
    }
}