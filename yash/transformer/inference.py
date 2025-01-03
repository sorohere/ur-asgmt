import torch
import requests
from PIL import Image
from transformers import pipeline


class LLavaInference:
    """
    A class to handle inference using a locally saved LLava model.

    Attributes:
        model_directory (str): Directory where the LLava model is saved.
        max_new_tokens (int): Maximum number of tokens to generate during inference.
    """

    def __init__(self, model_directory: str, max_new_tokens: int = 200):
        """
        Initializes the LLavaInference class with the model directory and parameters.

        Args:
            model_directory (str): Directory where the LLava model is saved.
            max_new_tokens (int, optional): Maximum tokens for generated text. Defaults to 200.
        """
        self.model_directory = model_directory
        self.max_new_tokens = max_new_tokens
        self.pipe = None

    def load_model(self):
        """
        Loads the locally saved LLava model.

        Raises:
            Exception: If there is an issue with loading the model.
        """
        print("Loading the saved model...")
        try:
            self.pipe = pipeline("image-to-text", model=self.model_directory)
            print("Model loaded successfully.")
        except Exception as e:
            raise Exception(f"Error loading the model: {e}")

    def prepare_image(self, image_path: str = None, image_url: str = None) -> Image.Image:
        """
        Prepares the image from a file path or a URL.

        Args:
            image_path (str, optional): Path to the image file.
            image_url (str, optional): URL of the image to download.

        Returns:
            PIL.Image.Image: The prepared image.

        Raises:
            ValueError: If neither `image_path` nor `image_url` is provided.
        """
        if image_path:
            print("Loading image from file...")
            return Image.open(image_path)
        elif image_url:
            print("Downloading and preparing the image from URL...")
            return Image.open(requests.get(image_url, stream=True).raw)
        else:
            raise ValueError("Either image_path or image_url must be provided.")

    def generate_text(self, image: Image.Image, user_query: str) -> str:
        """
        Generates text based on the input image and user query.

        Args:
            image (PIL.Image.Image): The input image.
            user_query (str): The query provided by the user.

        Returns:
            str: The generated text.
        """
        if self.pipe is None:
            raise Exception("Model is not loaded. Please load the model first.")

        prompt = f'USER: <image>\n{user_query}\nASSISTANT:'
        print("Performing inference...")
        outputs = self.pipe(image, prompt=prompt, generate_kwargs={"max_new_tokens": self.max_new_tokens})
        return outputs[0]["generated_text"]


if __name__ == "__main__":
    # Define parameters
    model_directory = "./llava_model"
    image_path = "test.png"  # Path to a local image file
    user_query = input("Enter a query: ")

    # Create an instance of LLavaInference
    llava_inference = LLavaInference(model_directory=model_directory)

    # Load the model
    llava_inference.load_model()

    # Prepare the image
    image = llava_inference.prepare_image(image_path=image_path)

    # Generate text
    generated_text = llava_inference.generate_text(image=image, user_query=user_query)

    # Display the result
    print("Generated text:")
    print(generated_text)
