import fs from 'fs';
import path from 'path';

exports.handler = async (event, context) => {
  try {
    // Read the JSON file synchronously
    const jsonPath = path.resolve(__dirname, './trainData.json');
    const fileContents = fs.readFileSync(jsonPath, 'utf8');
    
    
    // Parse JSON data
    const trains = JSON.parse(fileContents);

    // Return the JSON data in the response
    return {
      statusCode: 200,
      body: JSON.stringify(trains),
      headers: {
        'Content-Type': 'application/json',
      },
    };
  } catch (error) {
    console.error('Error reading JSON file:', error);
    
    // Handle errors and return a 500 status
    return {
      statusCode: 500,
      body: JSON.stringify({ message: 'Internal Server Error' }),
    };
  }
};
