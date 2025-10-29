# Heart Rate Prediction Program
# By Muhammad Mustafa

# Function to classify activity based on heart rate difference
def classify_activity(resting_hr, post_exercise_hr):
    difference = post_exercise_hr - resting_hr
    if difference > 40:
        return "Highly Active"
    elif difference > 20:
        return "Active"
    elif difference > 10:
        return "Moderately Active"
    else:
        return "Non-Active"

# Input section
print("---- Heart Rate and Activity Prediction ----")

age = int(input("Enter your Age (in years): "))
gender = input("Enter your Gender (Male/Female): ")
resting_hr = int(input("Enter your Resting Heart Rate (bpm): "))
post_exercise_hr = int(input("Enter your Post-Exercise Heart Rate (bpm): "))

# Calculate average heart rate
average_hr = (resting_hr + post_exercise_hr) / 2

# Classify activity
activity_label = classify_activity(resting_hr, post_exercise_hr)

# Output results
print("\n------ Results ------")
print(f"Age: {age} years")
print(f"Gender: {gender}")
print(f"Average Heart Rate: {average_hr:.2f} bpm")
print(f"Activity Status: {activity_label}")
