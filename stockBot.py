import pyautogui
import cv2
import pytesseract
import numpy as np
import time  # Import the time module

# Set the path to the Tesseract executable
pytesseract.pytesseract.tesseract_cmd = r'C:\Program Files\Tesseract-OCR\tesseract.exe'

# Define the region for the screenshot
x, y, width, height = (16, 908, 347, 999)

while True:
    # Capture the screenshot in the defined region
    screenshot = pyautogui.screenshot(region=(x, y, width, height))

    # Convert the screenshot to a NumPy array
    screenshot_np = np.array(screenshot)

    # Extract text using Tesseract
    extracted_text = pytesseract.image_to_string(screenshot_np)

    try:
        # Extract numbers from the recognized text
        numbers = [float(num) for num in extracted_text.split() if num.replace('.', '', 1).isdigit()]
        if numbers:
            currentPrice = numbers[0]  # Assuming the first number found is the one you want
            print("Current Price:", currentPrice)
        else:
            print("No numbers found in the screenshot")
    except ValueError:
        print("Price extraction failed")

    # Add a delay between attempts (e.g., 1 second)
    time.sleep(0.5)
