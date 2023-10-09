package com.example.datencechatbotapp

import com.example.datencechatbotapp.models.QuestionItem

var AllQuestionItems = mutableListOf<QuestionItem>(
    QuestionItem(
        1,
        "What is the type of data you want to collect?",
        mutableMapOf(
            ("Full name" to false),
            ("Gender" to false),
            ("Sexual Orientation" to false),
            ("Date of Birth" to false),
            ("Aadhar detail" to false),
            ("PAN" to false),
            ("Email" to false),
            ("Phone Number" to false),
            ("Geo-location Data" to false),
            ("Health Information" to false),
            ("Social Media Information" to false),
        )
    ),
    QuestionItem(
        2,
        "Who are the data subjects?",
        mutableMapOf(
            ("Employees" to false),
            ("Existing Customers" to false),
            ("Potential Customers" to false),
            ("Vendors/Suppliers" to false),
            ("Contractors/Freelancers" to false),
            ("Clients" to false),
            ("Shareholders/Investors" to false),
            ("Job Applicants" to false),
            ("Website Visitors" to false),
        )
    ),
    QuestionItem(
        4,
        "What is the purpose of data collection?",
        mutableMapOf(
            ("Marketing Campaigns" to false),
            ("Customer Relationship Management (CRM)" to false),
            ("Sales and Lead Generation" to false),
            ("Market Research" to false),
            ("Product Development" to false),
            ("Customer Support" to false),
            ("Personalization" to false),
            ("Fraud Prevention" to false),
            ("User Behavior Analysis" to false),
            ("Performance Tracking" to false),
            ("Social Media Engagement" to false),
        )
    ),
    QuestionItem(
        7,
        "Does your company have a special mechanism to handle personal/sensitive data?",
        mutableMapOf(
            ("Transparency" to false),
            ("Informed Consent" to false),
            ("Opt-In Mechanism" to false),
            ("Granular Consent" to false),
            ("Withdrawal of Consent" to false),
            ("Disclosing Third-Party Sharing" to false),
            ("Limited Data Use" to false),
            ("Data Deletion Requests" to false),
            ("Data Breach Notification" to false),
            ("Providing Privacy Settings and Controls" to false),
            ("Consent for Automated Decision-Making" to false),
        )
    ),
    QuestionItem(
        8,
        "Does your company have a mechanism in place to securely store data?",
        mutableMapOf(
            ("Data Classification" to false),
            ("Secure Data Centers" to false),
            ("Firewalls and Intrusion Detection/Prevention Systems (IDS/IPS)" to false),
            ("Data Leakage Prevention (DLP)" to false),
            ("Regular Security Audits" to false),
            ("Data Access Monitoring and Logging" to false),
            ("Secure Database Management" to false),
            ("Physical Security Measures" to false),
            ("Secure File Encryption" to false),
            ("Patch Management" to false),
        )
    ),
    QuestionItem(
        9,
        "Does your company have procedures to assess and mitigate privacy risks in product or service development?",
        mutableMapOf(
            ("Privacy Impact Assessment (PIA)" to false),
            ("Data Mapping" to false),
            ("Purpose Specification" to false),
            ("Secure Data Storage" to false),
            ("Third-Party Risk Assessment" to false),
            ("Internal Privacy Review Board" to false),
            ("User Testing and Feedback" to false),
            ("Privacy Compliance Review" to false),
            ("Privacy Training for Developers" to false),
        )
    )
)
