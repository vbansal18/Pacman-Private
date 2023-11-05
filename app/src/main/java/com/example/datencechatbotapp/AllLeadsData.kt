package com.example.datencechatbotapp

import com.example.datencechatbotapp.models.Firm
import com.example.datencechatbotapp.screens.leadgeneration.setCurrentDesc
import com.example.datencechatbotapp.screens.leadgeneration.setCurrentLogo

object AllLeadsData{
    val SPICE_ROUTE = Firm("SPICE ROUTE", setCurrentDesc(name = "SPICE ROUTE"), "data@spiceroutelegal.com", "https://spiceroutelegal.com/", setCurrentLogo(name = "SPICE ROUTE"))
    val IKIGAI_LAW = Firm("IKIGAI LAW", setCurrentDesc(name = "IKIGAI LAW"), "aparajita@ikigailaw.com", "https://www.ikigailaw.com/", setCurrentLogo(name = "IKIGAI LAW"))
    val SAMVAD_PARTNERS = Firm("SAMVAD PARTNERS", setCurrentDesc(name = "SAMVAD PARTNERS"), "neela@samvadpartners.com", "https://www.samvadpartners.com/", setCurrentLogo(name = "SAMVAD PARTNERS"))
    val DSK_LEGAL = Firm("DSK LEGAL", setCurrentDesc(name = "DSK LEGAL"), "contactus@dsklegal.com", "https://dsklegal.com/", setCurrentLogo(name = "DSK LEGAL"))
    val INDUS_LAW = Firm("INDUS LAW", setCurrentDesc(name = "INDUS LAW"), "namita.viswanath@induslaw.com", "https://induslaw.com/", setCurrentLogo(name = "INDUS LAW"))
    val KHAITAN = Firm("KHAITAN & CO.", setCurrentDesc(name = "KHAITAN & CO."), "anand.mehta@khaitanco.com", "https://www.khaitanco.com/", setCurrentLogo(name = "KHAITAN & CO."))
    val TRILEGAL = Firm("TRILEGAL", setCurrentDesc(name = "TRILEGAL"), "jyotsna.jayaram@trilegal.com", "https://trilegal.com/", setCurrentLogo(name = "TRILEGAL"))
    val AZB_AND_PARTNERS = Firm("AZB AND PARTNERS", setCurrentDesc(name = "AZB AND PARTNERS"), "shagun.badhwar@azbpartners.com", "https://www.azbpartners.com/", setCurrentLogo(name = "AZB AND PARTNERS"))
    val MAJMUDAR_AND_PARTNERS = Firm("MAJMUDAR AND PARTNERS", setCurrentDesc(name = "MAJMUDAR AND PARTNERS"), "mailbox@majmudarindia.com", "https://www.majmudarindia.com/", setCurrentLogo(name = "MAJMUDAR AND PARTNERS"))

}