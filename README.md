# Reminder-app
Simple app with personalized reminders that are controlled by bluetooth connection.
Users can add, edit and delete reminders. It is possible to specify whether the reminder should be triggered
after losing the Bluetooth connection with the other device, or should it act as a normal system alarm. Users can read statistics and choose one of two color modes. The interface is designed from high-quality prototypes.
Data is stored with inbuilt SQLite database. Application was made by using only Java language.  
![image](https://user-images.githubusercontent.com/84517586/163011588-b82e6507-e2f4-4931-a15c-edd2950f4985.png)

1.Tapping on plus icon redirects you to new reminder activity. In there user can personalize new reminder by entering name or selecting default one, choosing days from radio buttons, color of its background and switch between bluetooth/normal alarms. Depending on type there is a possibility of selecting fixed time for the reminder to be displayed.

![image](https://user-images.githubusercontent.com/84517586/163008526-08a66b20-34e0-4568-889b-4a86f47ed035.png) 
![image](https://user-images.githubusercontent.com/84517586/163008566-86901fbd-b0bc-4da6-a7e2-60ac1b9f9206.png)


![image](https://user-images.githubusercontent.com/84517586/163008657-8c482f0c-411c-4ff0-a6a7-1865bc68ee0b.png)

2.After adding a new activity, the application returns to its main activity. You can notice that the added item in the center of the screen is displayed as a list item. The reminder view includes the most important user-defined attributes, such as:  
•	Name  
• Reminder on / off switch  
• Highlighted days of the week on which the reminder is active  
• Reminder type icon  
•	Colour  
3.In the options and statistics activity you can switch between the color mode by the "Tryb Dzień/Noc" switch. You can also check the name of the bluetooth device with which you are connected. If there is no connection, the word "brak" is displayed as in the picture above. Below is a set of statistics for the last five days on the graph as well as the "STATYSTYKI" button displaying detailed information.  
![image](https://user-images.githubusercontent.com/84517586/163009435-259f4c9e-773f-42c4-88ec-2fbf018cc12f.png)
![image](https://user-images.githubusercontent.com/84517586/163009724-d03f1c16-faa0-4663-ab2c-fe56daa5668d.png)
![image](https://user-images.githubusercontent.com/84517586/163011433-3eeefa80-2c90-4085-ae9a-fc2a7d374cc0.png)  
The statistics window is divided into three tabs, each of them contains
detailed information on reminders from the following periods: 7 days, 30 days and the entire period. All reminders are divided into forgotten and not forgotten by the user, which in turn are compared with the statistics of the preceding period.

User can delete/edit objects either by swiping or tapping on object.  
![image](https://user-images.githubusercontent.com/84517586/163010346-3e574c5f-7550-404b-bb96-9e00f62f685b.png)
![image](https://user-images.githubusercontent.com/84517586/163010365-64d35488-5abe-4ad8-ad13-e9d666fa78db.png)

