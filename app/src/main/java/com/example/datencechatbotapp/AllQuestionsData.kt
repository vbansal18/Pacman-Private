package com.example.datencechatbotapp

import com.example.datencechatbotapp.models.QuestionItem
import com.example.datencechatbotapp.models.TagItem

var AllQuestionItems = mutableListOf<QuestionItem>(
    QuestionItem(
        1,
        "What is the type of data you want to collect?",
        mutableListOf(
            TagItem("Full name" , false),
            TagItem("Gender" , false),
            TagItem("Sexual Orientation" , false),
            TagItem("Date of Birth" , false),
            TagItem("Aadhar detail" , false),
            TagItem("PAN" , false),
            TagItem("Email" , false),
            TagItem("Phone Number" , false),
            TagItem("Geo-location Data" , false),
            TagItem("Health Information" , false),
            TagItem("Social Media Information" , false),
        )
    ),
    QuestionItem(
        2,
        "Who are the data subjects?",
        mutableListOf(
            TagItem("Employees" , false),
            TagItem("Existing Customers" , false),
            TagItem("Potential Customers" , false),
            TagItem("Vendors/Suppliers" , false),
            TagItem("Contractors/Freelancers" , false),
            TagItem("Clients" , false),
            TagItem("Shareholders/Investors" , false),
            TagItem("Job Applicants" , false),
            TagItem("Website Visitors" , false),
        )
    ),
    QuestionItem(
        4,
        "What is the purpose of data collection?",
        mutableListOf(
            TagItem("Marketing Campaigns" , false),
            TagItem("Customer Relationship Management (CRM)" , false),
            TagItem("Sales and Lead Generation" , false),
            TagItem("Market Research" , false),
            TagItem("Product Development" , false),
            TagItem("Customer Support" , false),
            TagItem("Personalization" , false),
            TagItem("Fraud Prevention" , false),
            TagItem("User Behavior Analysis" , false),
            TagItem("Performance Tracking" , false),
            TagItem("Social Media Engagement" , false),
        )
    ),
    QuestionItem(
        7,
        "Does your company have a special mechanism to handle personal/sensitive data?",
        mutableListOf(
            TagItem("Transparency" , false),
            TagItem("Informed Consent" , false),
            TagItem("Opt-In Mechanism" , false),
            TagItem("Granular Consent" , false),
            TagItem("Withdrawal of Consent" , false),
            TagItem("Disclosing Third-Party Sharing" , false),
            TagItem("Limited Data Use" , false),
            TagItem("Data Deletion Requests" , false),
            TagItem("Data Breach Notification" , false),
            TagItem("Providing Privacy Settings and Controls" , false),
            TagItem("Consent for Automated Decision-Making" , false),
        )
    ),
    QuestionItem(
        8,
        "Does your company have a mechanism in place to securely store data?",
        mutableListOf(
            TagItem("Data Classification" , false),
            TagItem("Secure Data Centers" , false),
            TagItem("Firewalls and Intrusion Detection/Prevention Systems (IDS/IPS)" , false),
            TagItem("Data Leakage Prevention (DLP)" , false),
            TagItem("Regular Security Audits" , false),
            TagItem("Data Access Monitoring and Logging" , false),
            TagItem("Secure Database Management" , false),
            TagItem("Physical Security Measures" , false),
            TagItem("Secure File Encryption" , false),
            TagItem("Patch Management" , false),
        )
    ),
    QuestionItem(
        9,
        "Does your company have procedures to assess and mitigate privacy risks in product or service development?",
        mutableListOf(
            TagItem("Privacy Impact Assessment (PIA)" , false),
            TagItem("Data Mapping" , false),
            TagItem("Purpose Specification" , false),
            TagItem("Secure Data Storage" , false),
            TagItem("Third-Party Risk Assessment" , false),
            TagItem("Internal Privacy Review Board" , false),
            TagItem("User Testing and Feedback" , false),
            TagItem("Privacy Compliance Review" , false),
            TagItem("Privacy Training for Developers" , false),
        )
    )
)
