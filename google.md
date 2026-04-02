
# Google OAuth Setup (Google Cloud Console Only)

## 🚀 Step 1: Create Project

Open:
https://console.cloud.google.com/projectcreate

### Steps:
1. Enter Project Name
2. Click Create
3. Wait until project is selected

--

## 🔐 Step 2: Configure OAuth Consent Screen

Open:
https://console.cloud.google.com/apis/credentials/consent

### Steps:
1. Select **External**
2. Fill:
   - App Name
   - User Support Email
   - Developer Email
3. Click **Save & Continue**
4. Skip scopes
5. Add your email in **Test Users**
6. Click Finish

—

## 🔑 Step 3: Create OAuth Client ID

Open:
https://console.cloud.google.com/apis/credentials

### Steps:
1. Click **+ CREATE CREDENTIALS**
2. Select **OAuth client ID**
3. Choose:
   Application type → Web application

—

## 🌍 Step 4: Configure URLs

### Authorized JavaScript Origins:
http://localhost:5173

### Authorized Redirect URIs (optional):
http://localhost:8080/login/oauth2/code/google

—

## 📋 Step 5: Copy Credentials

After creation, you wil