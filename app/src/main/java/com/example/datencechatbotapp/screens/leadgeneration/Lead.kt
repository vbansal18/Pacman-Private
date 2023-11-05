package com.example.datencechatbotapp.screens.leadgeneration

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.datencechatbotapp.AllLeadsData
import com.example.datencechatbotapp.R
import com.example.datencechatbotapp.models.Firm
import com.example.datencechatbotapp.models.LeadsDataClass
import com.google.gson.Gson
import kotlinx.coroutines.async
import kotlin.text.Typography.bullet

@Composable
fun LeadGen(currentLeadIndex_: Int?, allLeads_: String?, navController: NavHostController) {
    var currentLeadIndex by remember {
        mutableStateOf(currentLeadIndex_!!)
    }
    val context = LocalContext.current
    val gson = Gson()
    var allLeads by remember { mutableStateOf<LeadsDataClass?>(null) }
    var currentLead by remember { mutableStateOf<Firm?>(null) }
    val activity = LocalView.current
    val composeEmail: ActivityResultLauncher<Intent> = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Handle the result, if needed
    }

    LaunchedEffect(Unit) {
        val parsedleads = async { gson.fromJson(allLeads_, LeadsDataClass::class.java) }
        allLeads = parsedleads.await()
        currentLead = setCurrentLead(allLeads!!, currentLeadIndex)
        Log.d("TAG", "In launchedEffect : allLeads: $allLeads")
    }
    println("currentLeadIndex : $currentLeadIndex")
    println("allLeads : $allLeads")
    if(currentLead!=null){
        currentLead = setCurrentLead(allLeads!!, currentLeadIndex)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.secondary,
                            MaterialTheme.colorScheme.secondary,
                            MaterialTheme.colorScheme.primary
                        )
                    )
                )
                .padding(15.dp)
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(20.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                    },
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                ) {
                    Text(
                        text = "<",
                        color = MaterialTheme.colorScheme.surface,
                        textAlign = TextAlign.Start,
                        fontSize = 22.sp,
                        fontWeight = FontWeight(300),
                        modifier = Modifier
                            .scale(scaleY = 1.5f, scaleX = 0.8f)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(
                                    color = Color.Black,
                                    bounded = true,
                                    radius = 20.dp
                                ),
                                onClick = {
                                    currentLeadIndex -= 1
                                    if (currentLeadIndex < 0) {
                                        currentLeadIndex += 3
                                    }
                                }
                            )
                    )
                }
                Button(
                    onClick = {
                    },
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                ) {
                    Text(
                        text = ">",
                        color = MaterialTheme.colorScheme.surface,
                        textAlign = TextAlign.End,
                        fontSize = 22.sp,
                        fontWeight = FontWeight(300),
                        modifier = Modifier
                            .scale(scaleY = 1.5f, scaleX = 0.8f)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(
                                    color = Color.Black,
                                    bounded = true,
                                    radius = 20.dp
                                ),
                                onClick = {
                                    currentLeadIndex += 1
                                    if (currentLeadIndex >= 3) {
                                        currentLeadIndex %= 3
                                    }
                                }
                            )
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(vertical = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = currentLead!!.imageLogo),
                    contentDescription = "leadgen",
                    Modifier
                        .width(150.dp)
                        .height(100.dp),
                    contentScale = ContentScale.FillWidth
                )
                Text(
                    text = currentLead!!.name,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.surface,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Text(
                        text = "Website : ",
                        color = MaterialTheme.colorScheme.surface,
                        fontSize = 14.sp
                    )
                    Text(
                        text = currentLead!!.website,
                        color = Color(153, 157, 255, 255),
                        fontSize = 14.sp,
                        modifier = Modifier.clickable {
                            openWebsite(context,currentLead!!.website)
                        },
                        textAlign = TextAlign.Center
                    )
                }
            }
            Divider(
                thickness = Dp.Hairline,
                color = MaterialTheme.colorScheme.surface,
            )
            val bottomFade = Brush.verticalGradient(0.8f to Color.Black, 1f to Color.Transparent)

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.85f)
                    .padding(20.dp)
                    .fadingEdge(bottomFade),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                val messages = currentLead!!.description
                val paragraphStyle = ParagraphStyle(textIndent = TextIndent(restLine = 12.sp))

                items(1) {
                    Text(
                        buildAnnotatedString {
                            messages.forEach {
                                withStyle(style = paragraphStyle) {
                                    append(bullet)
                                    append("\t\t")
                                    append(it)
                                    append("\n")
                                }
                            }
                        },
                        color = MaterialTheme.colorScheme.surface,
                    )
                }
            }
            Button(
                onClick = {
                    val recipientEmail = currentLead!!.email // Change to your recipient's email
                    val subject = "Collaboration Opportunity: Data Protection Regulation Compliance Assistance"
                    val message = "Dear ${currentLead!!.name}, \n" +
                            "\n" +
                            " \n" +
                            "I hope this email finds you in good spirits. We are reaching out to explore the possibility of collaborating with your esteemed law firm to ensure our compliance with data protection regulations. Our company, [Your Company Name], operates in [Your Industry] and handles data daily. In light of the evolving data protection landscape, we seek your expertise in conducting a comprehensive audit of our existing practices, revising our privacy policies, assisting in data breach response planning, and ensuring our employees are well-versed in data protection laws. We believe your guidance will be invaluable. To provide you with a comprehensive understanding of our current data protection framework, a PDF report detailing our existing data protection policies and practices is attached for your review. \n" +
                            "\n" +
                            "\n" +
                            "We would appreciate the opportunity to discuss the specifics further. Please let us know a suitable time for a meeting or call. Your assistance will not only fortify our compliance efforts but also enhance the trust of our clients and stakeholders. Thank you for considering our request, and we look forward to the possibility of working together. \n" +
                            "\n" +
                            " \n" +
                            "\n" +
                            "Warm regards, \n" +
                            " \n" +
                            "\n" +
                            "[Your Name] \n" +
                            "\n" +
                            "[Your Title] \n" +
                            "\n" +
                            "[Your Company Name] \n" +
                            "\n" +
                            "[Your Email Address] \n" +
                            "\n" +
                            "[Your Phone Number] "

                    val uri = Uri.parse("mailto:$recipientEmail?subject=${Uri.encode(subject)}&body=${Uri.encode(message)}")
                    val emailIntent = Intent(Intent.ACTION_SENDTO, uri)

                    composeEmail.launch(emailIntent)

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(213, 245, 111, 255),
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth(.7f)
            ) {
                Text(
                    text = "Connect",
                    color = Color(75, 75, 75, 255),
                    fontSize = 18.sp,
                )
            }
        }
    }
}

private fun openWebsite(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}
fun setCurrentLogo(name: String): Int {
    return when (name) {
        "SPICE ROUTE" -> R.drawable.spice_route_legal
        "IKIGAI LAW" -> R.drawable.ikigai_law
        "SAMVAD PARTNERS" -> R.drawable.samvad_partners_i
        "DSK LEGAL" -> R.drawable.dsk_legal
        "INDUS LAW" -> R.drawable.indus_law
        "KHAITAN & CO." -> R.drawable.khaitan_co_indian
        "TRILEGAL" -> R.drawable.trilegal
        "AZB AND PARTNERS" -> R.drawable.azb_partners
        "MAJMUDAR AND PARTNERS" -> R.drawable.majmudar_partners
        else -> R.drawable.baseline_person_24
    }
}
fun setCurrentDesc(name: String): List<String> {
    return when (name) {
        "SPICE ROUTE" -> listOf("Widely regarded as the best data protection and privacy group in India, our team combines the unique experience of having advised most of the market leaders across all sectors on data protection strategy, compliance, commercial arrangements involving data, global data transfers, artificial intelligence and machine learning, litigation and commercial negotiations", " Members of the team have over two decades of experience advising corporates on data issues and have been closely involved in the matters that have defined this area of law.", "The team regularly advises not just on Indian law but also on global data protection law and compliance.")
        "IKIGAI LAW" -> listOf("We help clients tailor their approach towards data management based on their business needs and applicable laws. We stress-test business models against legal requirements to assess impact on business practices like cross-selling, monetizing data, underwriting customers through data. We also advise clients on embedding data protection principles into tech architecture, UI/UX and business processes.", "Advised a company that uses machine learning to collect and structure patient health records with a focus on school-going children. We structured its contracts with various stakeholders - including doctors, hospitals and educational institutions- with a key focus on protecting sensitive personal data. Advised Cutting Chai Content on its data handling practices and the applicability of global privacy laws.Undertook a clause-by-clause analysis of the various drafts of India’s upcoming personal data protection law for a global cloud service provider, and assessed their implications on the client’s India operations.")
        "SAMVAD PARTNERS" -> listOf("The Firm has assisted Indian as well as international clients to navigate the complex and growing area of data compliance. We have assisted both our Indian and international clients with compliance on existing data protection requirements. We have also advised clients on specific issues that could arise from upcoming changes in the landscape of Indian data protection.", "International data protection legislations like the GDPR are equally relevant to many of our clients and we have worked with international counsel to ensure that our clients are able to achieve GDPR compliance as well.")
        "DSK LEGAL" -> listOf("Technology has paved way for innovative business models and constantly evolving ideals in the business world as we know it today. With corporate houses competing with one another to develop the best technologies, disrupt sectors, or even vying to follow the winds of change, there is a need to place reliance on simple, sound and straightforward legal counsel.", "The Technology practice of our Firm is spearheaded by a team of dedicated lawyers who possess a unique blend of technological knowledge and sectoral expertise. Our belief in providing clear, effective and business friendly legal advice in alignment with our client’s commercial aspirations and needs, has helped us in becoming ‘trusted advisors’ of our clients.", "Our widely-recognised and respected technology team has been providing clients with unrivalled counsel on a range of subject matters spanning across diverse industries and sectors, as briefly explained below:", "TECHNOLOGY TRANSACTIONS\n" +
                "Our Firm advises start-ups as well as industry heavyweights on myriad commercial transactions that include strategic collaborations, joint ventures, mergers and acquisitions, technology provider agreements, manufacturing and supply agreements, supply chain agreements, technology licensing agreements, outsourcing agreements, amongst others.", "INFORMATION TECHNOLOGY\n" +
                "We provide end-to-end legal and regulatory advisory relating to development, sourcing, deployment and use of various information technologies, assets, communications, networks and related ecosystems by our clients. We have advised clients on software licensing and development transactions, technology and know-how transfer arrangements, content licensing, website development contracts, master services agreements, etc.", "DATA GOVERNANCE\n" +
                "Our data protection practice comprises of the following key service areas, namely, privacy and data protection compliance management (advising on end-to-end data protection framework), transactional advisory (agreements for transfer, storage, disclosure, licensing, etc. of data) and incident response (advisory on security incidents and data breaches).", "FINTECH AND PAYMENTS\n" +
                "We advise on all aspects of the FinTech sector and all fintech and payment ecosystem participants including banks, financial institutions, venture capital funds, banks and financial institutions (including non-banking financial companies and technology platforms) looking to invest in venture capital, new technology or innovative strategies, and start-up and emerging growth companies. We also advise established players on leveraging technological innovation, protecting and enhancing their technology and trade secrets and establishing efficient compliance with applicable law and regulation.", "DIGITAL BUSINESSES\n" +
                "With a favourable regulatory framework in India and emerging technologies taking centre-stage, the offline businesses in India in present times seek to achieve a delicate balance between their physical and online presence to utilize the offline footprint of their customers on online mediums for delivering a nuanced customer experience. We provide leading manufacturers, e-commerce marketplaces, retailers, e-tailers, service providers, and other supply chain partners (both offline and online) with the regulatory and legal guidance that is required to navigate through these challenging terrains and execute their online business strategies in India.", "POLICY AND ADVOCACY\n" +
                "The laws governing the technologies in each sector are evolving rapidly and the governments across the world are trying to grapple with the technological advancements in various fields ranging from e-commerce to internet of things to blockchain. As part of a continuous process of maintaining our expertise and credibility, we contribute to various policymaking initiatives including preparation of responses to consultation papers released by the Governments, providing periodic updates to clients on the implications of government proposals, possible legal challenges, etc.\n", "TECH DISPUTES\n" +
                "Our Firm’s lawyers are mindful of the needs of the business to check and control the incidence of counterfeiting, piracy and misuse of intellectual property and are well experienced in taking care of such contentious issues before courts and regulators in India. Every incidence of misuse need not land up in court and keeping the needs of business in view, we advise on various alternate dispute resolution strategies to curb such incidence of misuse such as, issuing take-down notices, obtaining injunctive relief, notifying data breach incidents, framing dispute resolution strategies, engaging with regulators, etc.")
        "INDUS LAW" -> listOf(" INDUSLAW's team advises investors and technology companies on a variety of transactional and regulatory matters across this sector.", "Our Technology, Media & Telecommunications practice advises on:  \n", "The regulatory framework relating to the ownership and provision of on-line goods and services", "Commerce & electronic data interchange advice", "Conversion from brick and mortar retail to e-retail", "Cloud based delivery of software", "SAAS, IAAS and PAAS model implementation and software delivery", "Structuring investments and exits", "Drafting, negotiating and reviewing equity subscription and shareholders agreements", "Drafting, negotiating and reviewing commercial agreements in relation to technology, media and telecommunication transaction", "Regulatory advice and communicating with the regulator or other governmental authorities in un-regulated sectors", "Issues relating to Foreign Direct Investment and Competition Law", "Privacy and data protection", "Disputes and their resolution")
        "KHAITAN & CO." -> listOf("The Practice\n" +
                "Our Privacy and Data Protection practice focuses on the evolving technological, business and legal issues relating to privacy and security of data. With India making rapid strides towards digitisation and creation of a robust legal framework dealing with privacy and data protection, there is an unprecedented need for businesses to upgrade their privacy standards to avoid potential liabilities and meet mandatory compliance standards. Our team has consistently adopted an innovative approach and engaged with clients on key compliance requirements (including conducting awareness and teach-in sessions) and on implementation of customised solutions for them.", "Our Privacy and Data Protection practice has been recognised as a 'Tier I' practice by Legal 500 in their Asia-Pacific 2021 rankings. We have also been awarded “Data Protection Law Firm of the Year” award at the ALB India Law Awards 2021 hosted by Asian Legal Business. Our team members have been recognized in the categories of  “Leading Individual” and “Recommended Lawyer” for data protection in Legal500 2021 Asia Pacific rankings.", "The Execution\n" +
                "We advise clients across the entire legal spectrum of privacy and data protection concerns, ranging from advice on general regulatory compliance, transaction related advice, drafting/review of policies, data processing agreements and other associated documents, cross-border data transfers and disclosures, government access requests, data security and breach notification, data retention, use of new age technologies, defence against investigations and proceedings regarding privacy/data protection violations or security failures. We also advise on data protection matters in certain niche sectors such as blockchain, internet of things, cloud, artificial intelligence, etc.", "Our clients include international and domestic multinational companies, including Fortune 100 and 500 companies, across industries such as financial services, insurance, digital media, information technology, social media, e-commerce, healthcare, medical/health technology, telemedicine, telecommunications, pharmaceuticals, educational technology, software/platform/infrastructure services, retail and manufacturing.", "The Khaitan Advantage\n" +
                "We draw upon our highly specialised team that has a deep regulatory understanding and our Partners have also been part of several industry body discussions in relation to privacy and data protection. Our Firm has one of the largest privacy and data protection practice spread across our offices, and draws expertise seamlessly from specialists in the Firm from arenas such as TMT; Competition/Antitrust; Employment; Intellectual Property; Dispute Resolution; White Collar Crime; Taxation, etc.", "Our team was extensively involved in consultation meets and sessions with the Justice Srikrishna Committee which formulated the first draft of the Personal Data Protection Bill. Our team has also been one of the few organisations from which inputs were sought directly by the Government of India on certain aspects of the Personal Data Protection Bill. Our team members’ views had been sought by the National Commission for Women (Government of India) regarding cyber-crime against women and changes in laws required to address issues. Our team members have been deeply involved with the Government and have been suggesting measures that can be incorporated to provide boost to India’s booming outsourcing industry where large volumes of data are exchanged. Our team members have conducted webinars jointly with the Department of Telecommunication, Ministry of Communications on issues including data localisation. Additionally, our team members have also submitted recommendations on the proposed draft regulations around non-personal data. Our team was also invited to our neighbouring country, Bangladesh, by BASIS (Bangladesh Association of Software and Information Services) to provide inputs to the lawmakers there for preparing their data protection law.", "Our team has been frequently invited to collaborate and give inputs on niche aspects of data protection to various publishing houses of national and international repute such as OneTrust Data Guidance, Thomson Reuters - Practical Law Data Privacy Advisor, International Comparative Law Journal, etc. Our team members are frequently quoted in national dailies and other reputed publications on the topic of privacy and data protection.")
        "TRILEGAL" -> listOf("Widely acknowledged as the pioneering technology practice in the country, we provide the most comprehensive coverage of the full range of issues in the sector. Our TMT team brings its unique understanding of the issues that lie at the intersection of technology, business, and the law to advise clients on a range of issues, from mergers and acquisitions and regulatory and commercial advice to policy advice and dispute resolution.", "Our expertise spans all facets of the technology, media and telecommunications landscape. Our team is widely regarded for its ability to evaluate new and complex technology business models to help our clients identify the most commercially viable ways to commercialise these models while still adhering to the legal and regulatory constraints in the sector.", "We work with technology companies looking either to provide services offshore from India or build their domestic operations here. We assist in drafting and negotiating cutting-edge technology agreements on areas as diverse as co-location, online user arrangements, payment portals, platforms, and the shared economy model. We regularly negotiate framework and local agreements across widespread jurisdictions, including Europe, North America, and South-East Asia. We are the preferred legal advisors for a wide range of emerging technology businesses in the FinTech, HealthTech, EdTech, RegTech, OTT, and streaming services sectors.", "In the Media sector, we help clients navigate advertising and marketing laws and make viable content campaigns and products and advise on ethics-related issues. We have advised clients on some of the most paradigm-shifting regulatory-related matters in the country as well as in respect of some of the largest telecom acquisitions in the country. We are preferred advisors for most large international telecom companies operating in India and regularly assist clients with government policy and regulatory issues, and frequently represented clients before the Department of Telecommunications and the Telecom Regulatory Authority of India.")
        "AZB AND PARTNERS" -> listOf("AZB & Partners is highly regarded by clients and our peer network, for our specialised and diverse experience in the privacy and data protection space. Our team comprises CIPP / E certified professionals, industry body representatives and sectoral experts in TMT, Fintech, healthcare, IT, intellectual property, e–commerce, blockchain, AI, automotive, electronics and financial and payment technologies. This enables us to better understand the technology and regulatory space our clients operate in and render nuanced advice.","Our lawyers work on both advisory and transactional mandates and often in a cross–jurisdictional context. We participate in stakeholder and regulatory consultations on the evolving privacy and data protection framework in India. Clients seek our assistance while preparing for depositions before Joint Parliamentary Committee (JPC) constituted to examine the PDP Bill.", "WHAT WE DO\n" +
                "AZB’s highly specialised privacy and data protection practice helps clients formulate their data protection management programme, develop data protection policies, document data flows within their organisation and categorise sensitive personal data to ensure that requisite controls are put in place to safeguard such data from threats.", "Our lawyers are adept at identifying legal and regulatory risks related to non–compliance with data protection frameworks and providing strategic opinions on the adoption of the ‘privacy by design’ principle while creating new technologies and systems.", "All client mandates are viewed independently and we closely engage with them from commencement till closure, sharing insights on critical regulatory and/or legislative developments. We also conduct training sessions within client organisations to educate them on compliance requirements relating to collection, handling and processing of personal data, guiding them on best practices to mitigate data breach risks.")
        "MAJMUDAR AND PARTNERS" -> listOf("We have a cutting-edge technology, media and telecommunications practice that has sealed many deals for major clients. We have extensive experience in advising clients on challenging and grey areas in this field. This practice area covers legal advice on fintech, health-tech, edu-tech, cloud computing, digital transformation, technology transfer deals, licensing, outsourcing and data privacy matters. We also advice on and draft security compliance policies for clients and advises clients on internet-related and work-from-home issues.")
        else -> listOf("Description")
    }
}

fun setCurrentLead(allLeads: LeadsDataClass, currentLeadIndex: Int?): Firm? {

    return when (allLeads.lawFirmNames[currentLeadIndex!!]) {
        "SPICE ROUTE" -> AllLeadsData.SPICE_ROUTE
        "IKIGAI LAW" -> AllLeadsData.IKIGAI_LAW
        "SAMVAD PARTNERS" -> AllLeadsData.SAMVAD_PARTNERS
        "DSK LEGAL" -> AllLeadsData.DSK_LEGAL
        "INDUS LAW" -> AllLeadsData.INDUS_LAW
        "KHAITAN & CO." -> AllLeadsData.KHAITAN
        "TRILEGAL" -> AllLeadsData.TRILEGAL
        "AZB AND PARTNERS" -> AllLeadsData.AZB_AND_PARTNERS
        "MAJMUDAR AND PARTNERS" -> AllLeadsData.MAJMUDAR_AND_PARTNERS
        else -> AllLeadsData.SPICE_ROUTE
    }
}

fun Modifier.fadingEdge(brush: Brush) = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithContent {
        drawContent()
        drawRect(brush = brush, blendMode = BlendMode.DstIn)
    }