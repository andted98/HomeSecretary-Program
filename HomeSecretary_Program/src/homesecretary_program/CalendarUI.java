//Author - Andrew Tedesco

package homesecretary_program;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.*;

public class CalendarUI extends JFrame {
    
    private JPanel headerPanel, calendarPanel;
    private JLabel monthLabel;
    
    //Creating an ArrayList for Calendar Appointments
    private Map<Integer, ArrayList<String>> appointments;

    public CalendarUI() {
        appointments = new HashMap<>();
        buildCalendarGUI();
        
        updateCalendar(Calendar.getInstance());
    }

    private void buildCalendarGUI() {
        
        setTitle("Calendar Screen");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);

        headerPanel = new JPanel();
        monthLabel = new JLabel("", JLabel.CENTER);
        calendarPanel = new JPanel(new GridLayout(0, 7));

        add(headerPanel, BorderLayout.NORTH);
        add(calendarPanel, BorderLayout.CENTER);

        updateUI();
        
    }

    private void updateUI() {
        
        headerPanel.setBackground(Color.DARK_GRAY);
        headerPanel.setPreferredSize(new Dimension(600, 40));

        monthLabel.setForeground(Color.WHITE);
        monthLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(monthLabel);
        
        calendarPanel.removeAll();
        String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

        //Add days of the week labels
        for (String day : daysOfWeek) {
            JLabel label = new JLabel(day, JLabel.CENTER);
            calendarPanel.add(label);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int maxDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        //Add blank cells for previous month days
        for (int i = 1; i < firstDayOfWeek; i++) {
            calendarPanel.add(new JLabel(""));
        }

        //Add cells for current month days with events
        for (int day = 1; day <= maxDaysInMonth; day++) {
            JLabel label = new JLabel(String.valueOf(day), JLabel.CENTER);
            ArrayList<String> dayEvents = appointments.getOrDefault(day, new ArrayList<>());

            if (!dayEvents.isEmpty()) {
                StringBuilder eventsText = new StringBuilder("<html>");
                for (String event : dayEvents) {
                    eventsText.append(event).append("<br>");
                }
                eventsText.append("</html>");
                label.setToolTipText(eventsText.toString());
                label.setForeground(Color.BLUE);
                Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
                label.setBorder(border);
            }

            label.addMouseListener(new MouseClickListener(day, dayEvents));

            calendarPanel.add(label);
        }

        pack();
    }

    public void updateCalendar(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        calendar.set(year, month, 1);

        monthLabel.setText(String.format("%tB %tY", calendar, calendar));

        updateUI();
    }

    public void addEvent(int day, String event) {
        appointments.computeIfAbsent(day, k -> new ArrayList<>()).add(event);
    }

    private class MouseClickListener implements java.awt.event.MouseListener {
        private final int day;
        private final ArrayList<String> events;

        public MouseClickListener(int day, ArrayList<String> events) {
            this.day = day;
            this.events = events;
        }

        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            if (!events.isEmpty()) {
                StringBuilder message = new StringBuilder("Events on " + day + ":\n");
                for (String event : events) {
                    message.append(event).append("\n");
                }
                JOptionPane.showMessageDialog(CalendarUI.this, message.toString());
            } else {
                JOptionPane.showMessageDialog(CalendarUI.this, "No events on day " + day);
            }
        }

        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {}

        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {}

        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {}

        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {}
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalendarUI::new);
    }
}
