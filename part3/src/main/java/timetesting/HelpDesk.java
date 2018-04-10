package timetesting;

import java.util.Calendar;

class HelpDesk {
    private static final int HOURS = 17;

    boolean willHandleIssue(Issue issue, Calendar calendar) {
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (Calendar.SUNDAY == dayOfWeek || Calendar.SATURDAY == dayOfWeek) {
            return false;
        }
        if (Calendar.FRIDAY == dayOfWeek) {
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour > HOURS) {
                return false;
            }
        }
        issue.getHandled();
        return true;
    }
}
