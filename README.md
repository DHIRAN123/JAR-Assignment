KIRANA STORE MANAGEMENT SYSTEM


Transaction API
Get All Transactions
Get a list of all transactions.
•	URL:-  http://localhost:8080/transactions
•	Method GET
Response
•	Success Response:
•	Code: 200 OK
•	Content:
[
    {
        "id": 1,
        "date": "2022-01-01",
        "description": "Transaction 1",
        "amount": 100.00,
        "currency": "USD"
    },
    {
        "id": 2,
        "date": "2022-01-02",
        "description": "Transaction 2",
        "amount": 150.00,
        "currency": "EUR"
    }
    
]

Record Transaction
Record a new transaction.
•	URL:-  http://localhost:8080 /transactions/record
•	Method POST
•	Request Body
{
    "date": "2022-01-03",
    "description": "New Transaction",
    "amount": 120.50,
    "currency": "GBP"
}

Response
•	Success Response:
•	Code: 200 OK
•	Content:
{
    "id": 3,
    "date": "2022-01-03",
    "description": "New Transaction",
    "amount": 120.50,
    "currency": "GBP"
}
Get Daily Reports
Get a list of transactions grouped by date.
•	URL:- http://localhost:8080/transactions/daily-reports
•	Method GET
Response
•	Success Response:
•	Code: 200 OK
•	Content:
{
    "2022-01-01": [
        {
            "id": 1,
            "description": "Transaction 1",
            "amount": 100.00,
            "currency": "USD"
        }
        
    ],
    "2022-01-02": [
        {
            "id": 2,
            "description": "Transaction 2",
            "amount": 150.00,
            "currency": "EUR"
        }
    
    ]
    
}
