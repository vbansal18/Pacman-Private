package com.example.datencechatbotapp

import androidx.compose.runtime.remember
import com.example.datencechatbotapp.models.QuestionItem
import com.example.datencechatbotapp.models.TagItem

var AllQuestionItems  =
    listOf(
        QuestionItem(
            1,
            "Which country are you based in?",
            null
        ),
        QuestionItem(
            2,
            "Select your Industry : ",
            mutableListOf(
                TagItem("Financial technology (fintech)", false),
                TagItem("Artificial intelligence (AI)", false),
                TagItem("Blockchain technology", false),
                TagItem("Data management", false),
                TagItem("Ecommerce", false),
                TagItem("Travel management", false),
                TagItem("EdTech", false),
                TagItem("Cybersecurity", false),
                TagItem("Leisure and entertainment", false),
                TagItem("Personalized travel and experiences", false),
                TagItem("Online food delivery", false),
            )

        ),
        QuestionItem(
            3,
            "What is the type of data that you want to collect?",
            mutableListOf(
                TagItem("Full name", false),
                TagItem("Gender", false),
                TagItem("Sexual Orientation", false),
                TagItem("Date of Birth", false),
                TagItem("Aadhaar detail", false),
                TagItem("PAN", false),
                TagItem("Email", false),
                TagItem("Phone Number", false),
                TagItem("Geo-location Data", false),
                TagItem("Health Information", false),
                TagItem("Social Media Information", false),
            )

        ),
        QuestionItem(
            4,
            "Who are the data subjects?",
            mutableListOf(
                TagItem("Employees", false),
                TagItem("Clients", false),
                TagItem("Job Applicants", false),
                TagItem("Vendors/Suppliers", false),
                TagItem("Existing Customers", false),
                TagItem("Potential Customers", false),
                TagItem("Contractors/Freelancers", false),
                TagItem("Shareholders/Investors", false),
                TagItem("Website Visitors", false),
            )
        ),
        QuestionItem(
            5,
            "From which country are you collecting the data?",
            null
        ),
        QuestionItem(
            6,
            "What is the purpose of data collection?",
            mutableListOf(
                TagItem("Marketing Campaigns", false),
                TagItem("Sales and Lead Generation", false),
                TagItem("Product Development", false),
                TagItem("User Behavior Analysis", false),
                TagItem("Customer Support", false),
                TagItem("Personalization", false),
                TagItem("Fraud Prevention", false),
                TagItem("Market Research", false),
                TagItem("Performance Tracking", false),
                TagItem("Social Media Engagement", false),
                TagItem("Customer Relationship Management (CRM)", false),
            )
        ),
        QuestionItem(
            7,
            "In which country are you storing the data?",
            null
        ),
        QuestionItem(
            8,
            "Do you collect children's data?",
            mutableListOf(
                TagItem("Yes", false),
                TagItem("No", false),
            )
        ),
        QuestionItem(
            9,
            "Does your company have a special mechanism to handle personal/sensitive data?",
            mutableListOf(
                TagItem("Transparency", false),
                TagItem("Informed Consent", false),
                TagItem("Opt-In Mechanism", false),
                TagItem("Granular Consent", false),
                TagItem("Withdrawal of Consent", false),
                TagItem("Limited Data Use", false),
                TagItem("Data Deletion Requests", false),
                TagItem("Data Breach Notification", false),
                TagItem("Providing Privacy Settings and Controls", false),
                TagItem("Disclosing Third-Party Sharing", false),
                TagItem("Consent for Automated Decision-Making", false),
            )
        ),
        QuestionItem(
            10,
            "Does your company have a mechanism in place to securely store data?",
            mutableListOf(
                TagItem("Data Classification", false),
                TagItem("Secure Data Centers", false),
                TagItem("Data Leakage Prevention (DLP)", false),
                TagItem("Regular Security Audits", false),
                TagItem("Secure Database Management", false),
                TagItem("Physical Security Measures", false),
                TagItem("Secure File Encryption", false),
                TagItem("Patch Management", false),
                TagItem("Data Access Monitoring and Logging", false),
                TagItem("Firewalls and Intrusion Detection/Prevention Systems (IDS/IPS)", false),
            )
        ),
        QuestionItem(
            11,
            "Does your company have procedures to assess and mitigate privacy risks in product or service development?",
            mutableListOf(
                TagItem("Data Mapping", false),
                TagItem("Purpose Specification", false),
                TagItem("Secure Data Storage", false),
                TagItem("Third-Party Risk Assessment", false),
                TagItem("Internal Privacy Review Board", false),
                TagItem("User Testing and Feedback", false),
                TagItem("Privacy Compliance Review", false),
                TagItem("Privacy Impact Assessment (PIA)", false),
                TagItem("Privacy Training for Developers", false),
            )
        )
    )
