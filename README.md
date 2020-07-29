# TicketSystem
## About the Application:
Its a Simple Spring boot Java RESTFUL Application which simulates the behaviour of a Tickiting System.

**There are few Assumptions which are as follows.**

1. This Application is used for making reservations in Auditorium whose size is taken as : 9 X 33 ( Rows X Columns). This configuration can be changed in config.properties files , if required.

2. This Application interacts with Mariadb as backend in order to store the state of the booking system.

3. The process of booking is as follows :
  a). Create a hold on the tickets by providing the number of seats and email ID ( No validation Check on Email ID).
  b). A Seat hold ID is returned to the user ( Seats or kept on hold for X number of seconds ; This Value is Configurable and is stored in config.properties file.
  c).User has to reserve the seats before the hold is expired by providing the Hold it else seats will be released back to the open pool.
  
  
4. Exception Handling is yet to be Added.

5. Details of the restful Operations:

  A. **GET** : http://localhost:9191/ticketService/seatsAvailable ***[Returns Total Availble Seats Open for Booking]***
  *Sample Respone:*
```
{
    "number": 290
}
```
  B. **POST** : http://localhost:9191/ticketService/findAndHoldSeats?seatCount=2&emailId=XYZ@gmail.com ***[Used to Hold the number of Seats passed in the request]***
  *Sample Respone:*
```
{
    "holdStatus": "SUCCESS",
    "seatList": [
        {
            "seatNumber": 8,
            "rowNumber": 0,
            "colNumber": 7,
            "reservationId": "-22074960",
            "status": "ONHOLD"
        },
        {
            "seatNumber": 9,
            "rowNumber": 0,
            "colNumber": 8,
            "reservationId": "-22074960",
            "status": "ONHOLD"
        }
    ],
    "holdStartTS": "2020-07-29T14:46:21.358+00:00",
    "emailID": "XYZ@gmail.com",
    "seatHoldID": -22074960
}
```

  C. **PATCH** : http://localhost:9191/ticketService/reserveSeats?onHoldId=1960160567 ***[Service used to Confirm the Booking by passing Hold Id]***
  *Sample Respone:*
```
{
    "bookingStatus": "SUCCESS",
    "seatList": null,
    "bookingTS": "2020-07-29T13:13:13.260+00:00",
    "emailID": "XYZ@gmail.com",
    "bookingID": 1960160567
}
```


