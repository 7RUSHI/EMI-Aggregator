# EMI Aggregator Notes

## Tasks

- Extract constants `Done`
- Complete Settings Screen `In-progress`
  - Complete onClicks
- UI Screens
  - Build Bottom Sheet to add,edit Society
  - Multi-Select List to delete Society
- Room DB Design
- Setup import/export DB
- Add Permission Check in Main Screen `Done`
- Data store to show Onboarding screen only once `Done`
- Build On-board Screen `Done`
- Setup Runtime Permissions: Storage, Notification `Done`
- Setup Multi-Language `Done`
- Setup Room `Done`
- Setup Hilt `Done`
- Setup Navigation `Done`
- Setup Notification `Done`
- Setup WorkManager `Done`
- Setup Material UI `Done`
- Setup Startup : WorkManager `Done`
- Document all screens
- Setup Dark mode

## Links

[How to Make a Clean Architecture Note App (MVVM / CRUD / Jetpack Compose) - Android Studio Tutorial](https://www.youtube.com/watch?v=8YPXv7xKh2w)
[The Ultimate Dagger-Hilt Guide (Dependency Injection) - Android Studio Tutorial](https://www.youtube.com/watch?v=bbMsuI2p1DQ)
[The ULTIMATE Guide to Sharing Data Between Screens in Jetpack Compose](https://www.youtube.com/watch?v=h61Wqy3qcKg)
[The ULTIMATE Permission Handling Guide (Showing rationale + Permanently Declined)](https://www.youtube.com/watch?v=D3JCtaK8LSU)
[Jetpack Compose Navigation for Beginners - Android Studio Tutorial](https://www.youtube.com/watch?v=4gUeyNkGE3g)
[The Full Guide About the DateTime API in Kotlin](https://www.youtube.com/watch?v=gzHy6wKAJh8)
[Local Notifications in Android - The Full Guide (Android Studio Tutorial)](https://www.youtube.com/watch?v=LP623htmWcI)
[THIS Is How You Use Notifications on Android API 33+ (Notification Permission)](https://www.youtube.com/watch?v=bHlLYhSrXvc)
[The Full Guide to ANNOTATIONS In Kotlin](https://www.youtube.com/watch?v=qdnhQzVGywQ)

## Screens

- Home screen
  - सोसायटी Add,Edit,Delete,?Archive
  - सोसायटी List
  - Menu - Settings
- Settings screen
  - Language - dialog selection
  - Schedule backup - dialog selection
  - Output folder - click
  - Export full backup - click
  - Export as CSV - click
  - Import data- click
  - Reset - click
  - About - click
  - Theme - Light, Dark - toggle
- Operation screen
  - जमा
    - शेअर्स, वर्गणी, मुदत
  - नांवे
    - EMI - round off to zero 
- Shares screen
- Deposit screen
- EMI screen

## Room

- Society
  - Columns - id(PK,NN), name(NN), created(DF,NN), modified(DF,NN), is_archived(NN)
- Member
  - Columns - id(PK,NN), society_id(FK,NN), is_archived(NN), name, shares, deposit,  

## Chat GPT

**App Name: EMI Aggregator**

**Description:**
EMI Aggregator is your comprehensive solution for effortlessly managing EMI payments and tracking the financial transactions of Housing Societies Members. This intuitive app simplifies the process of monitoring EMI payments, while also providing robust features to track deposits, shares, subscriptions, and terms.

**Key Features:**
1. **EMI Tracking:** Easily monitor and manage EMI payments for loans, ensuring timely payments and providing a clear overview of upcoming dues.

2. **Housing Societies Management:** Streamline the financial records of Housing Societies Members, making it convenient to track contributions, shares, and subscriptions.

3. **Deposit and Savings Tracking:** Keep a close eye on deposits and savings, allowing users to plan and achieve their financial goals effectively.

4. **Term Tracking:** Effortlessly track terms and tenures associated with financial transactions, ensuring accurate and organized record-keeping.

**Why Choose EMI Aggregator?**
- *Holistic Financial Management:* Consolidate all your financial data in one place, from EMI payments to society transactions, for a comprehensive financial overview.

- *User-Friendly Interface:* Navigate through financial data with ease, thanks to the app's intuitive design, making financial management accessible for everyone.

- *Real-Time Updates:* Stay informed with real-time updates on EMI payments, society transactions, and financial activities.

**Benefits:**
- **Efficiency:** Save time and reduce the hassle of manual record-keeping with an automated system that tracks and manages financial data seamlessly.

- **Financial Planning:** Empower users to make informed financial decisions by providing insights into their EMI payments, savings, and society transactions.

- **Organization:** Keep all financial records organized and easily accessible, ensuring accurate reporting and compliance.

**How EMI Aggregator Works:**
Simply input your EMI details, society transactions, and financial activities into the app. EMI Aggregator will provide a clear and organized overview of your financial landscape, helping you stay in control.

**Compatibility:**
EMI Aggregator is available for both iOS and Android devices, ensuring accessibility for users across different platforms.

**Download EMI Aggregator Today and Take Control of Your Finances:**
Experience the convenience of managing EMI payments and tracking society transactions effortlessly. Download EMI Aggregator now and take the first step toward a more organized and informed financial future.

**Stay Connected:**
Follow us on [social media links] for updates, tips, and support. For any inquiries or assistance, contact our support team at [support email/phone].