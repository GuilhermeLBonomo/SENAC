from flask import Flask
import random

app = Flask(__name__)

# Create here the code of queue data structure SENAC
# You have create the follow methods:
# put() => To add a new value into the linked structure (queue)
# get() => To recovery the element of linked structure (queue)



# ####################################



@app.route('/start_processing', methods=['POST'])
def start_queue_senac():
    '''Every time you run this route, you must add a new Node to the Queue'''
    number = random.randint(0,9)
    print(number, flush=True) # Example, We need to insert the variable number in the linked structure (queue)

    # You must call put() method
    # Your code here!

    return '', 200

@app.route('/pos_processing', methods=['POST'])
def processing_queue_senac():
    '''Every time you run this route you must retrieve the value from the queue. And run the code below!!'''
    # You must call get() method
    # Loop to empty the queue....
    # For each value, print the value multiplied by 1000 and put a delay of 30 seconds.
    # When the Queue is empty, inform on the screen (Print)
    # Your code here!

    print("Pos_Processing", flush=True)


    return '', 200



if __name__ == '__main__':
    app.run(debug=__debug__)
