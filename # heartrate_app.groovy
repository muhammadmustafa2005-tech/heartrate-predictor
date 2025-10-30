# heartrate_app.py
import streamlit as st
import matplotlib.pyplot as plt

# Page setup
st.set_page_config(page_title="Heart Rate & Activity Prediction", page_icon="💓", layout="centered")

# --- Page 1: User Info ---
st.title("💓 Welcome to Heart Rate & Activity Analyzer")

# Create session state to move between pages
if "user_info_submitted" not in st.session_state:
    st.session_state.user_info_submitted = False

if not st.session_state.user_info_submitted:
    st.subheader("Please enter your details to continue:")
    name = st.text_input("👤 Your Name")
    email = st.text_input("📧 Email Address")

    if st.button("Next ➡️"):
        if name.strip() == "" or email.strip() == "":
            st.warning("Please fill in all details before continuing.")
        else:
            st.session_state.user_info_submitted = True
            st.session_state.name = name
            st.session_state.email = email
            st.success(f"Welcome, {name}! Let's analyze your heart rate ❤️")

else:
    # --- Page 2: Heart Rate Inputs ---
    st.header("📊 Heart Rate & Activity Prediction")

    age = st.number_input("Enter your Age (years):", min_value=1, max_value=120, value=20)
    gender = st.selectbox("Select your Gender:", ["Male", "Female"])
    resting_hr = st.number_input("Resting Heart Rate (bpm):", min_value=30, max_value=150, value=70)
    post_exercise_hr = st.number_input("Post-Exercise Heart Rate (bpm):", min_value=40, max_value=220, value=110)

    def classify_activity(resting, post):
        diff = post - resting
        if diff > 40:
            return "Highly Active", "🏃 You’re highly active — great cardiovascular health!"
        elif diff > 20:
            return "Active", "💪 You’re active — maintain your exercise routine!"
        elif diff > 10:
            return "Moderately Active", "⚖️ Moderate activity — try adding a bit more movement daily."
        else:
            return "Non-Active", "🛌 You need more physical activity — take short walks & move regularly."

    if st.button("Analyze 💡"):
        avg_hr = (resting_hr + post_exercise_hr) / 2
        activity, advice = classify_activity(resting_hr, post_exercise_hr)

        # --- Results Section ---
        st.success(f"**Average Heart Rate:** {avg_hr:.2f} bpm")
        st.info(f"**Predicted Activity Level:** {activity}")
        st.write("💬 **Advice:**", advice)

        # --- Graph Section ---
        st.write("---")
        st.subheader("📈 Heart Rate Comparison")

        labels = ["Resting HR", "Post-Exercise HR"]
        values = [resting_hr, post_exercise_hr]

        fig, ax = plt.subplots()
        ax.bar(labels, values)
        ax.set_ylabel("Heart Rate (bpm)")
        ax.set_title("Resting vs Post-Exercise Heart Rate")
        st.pyplot(fig)

        st.write("---")
        st.write(f"👤 **Name:** {st.session_state.name}")
        st.write(f"📧 **Email:** {st.session_state.email}")
        st.write(f"🧠 **Age:** {age} years   •   **Gender:** {gender}")
