import torch
from transformers import pipeline, BitsAndBytesConfig

class LLavaModelHandler:
    """
    A class to handle loading, configuring, and saving the LLava model.

    Attributes:
        model_id (str): Identifier for the LLava model to be loaded.
        save_directory (str): Directory where the model will be saved.
        quantization_config (BitsAndBytesConfig): Configuration for 4-bit quantization.
    """

    def __init__(self, model_id: str, save_directory: str):
        """
        Initializes the LLavaModelHandler with model ID and save directory.

        Args:
            model_id (str): Identifier for the LLava model to be loaded.
            save_directory (str): Directory where the model will be saved.
        """
        self.model_id = model_id
        self.save_directory = save_directory
        self.pipe = None

    def load_model(self):
        """
        Loads the LLava model with the specified quantization configuration.

        Raises:
            Exception: If there is an issue with loading the model.
        """
        print("Loading the model...")
        try:
            self.pipe = pipeline("image-to-text", model=self.model_id)
            print("Model loaded successfully.")
        except Exception as e:
            raise Exception(f"Error loading the model: {e}")

    def save_model(self):
        """
        Saves the loaded model to the specified directory.

        Raises:
            Exception: If there is an issue with saving the model.
        """
        if self.pipe is None:
            raise Exception("Model is not loaded. Please load the model before saving.")
        
        print(f"Saving the model to {self.save_directory}...")
        try:
            self.pipe.save_pretrained(self.save_directory)
            print("Model saved successfully!")
        except Exception as e:
            raise Exception(f"Error saving the model: {e}")


if __name__ == "__main__":
    # Define parameters
    model_id = "llava-hf/llava-1.5-7b-hf"
    save_directory = "./llava_model"

    # Create an instance of LLavaModelHandler
    llava_handler = LLavaModelHandler(model_id, save_directory)

    # Load and save the model
    llava_handler.load_model()
    llava_handler.save_model()
