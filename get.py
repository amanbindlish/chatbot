import flask
from flask import request, jsonify

app = flask.Flask(__name__)
app.config["DEBUG"] = True

@app.route('/', methods=['GET'])
def home():
    return '''<h1>Distant Reading Archive</h1>
<p>A prototype API for distant reading of science fiction novels.</p>'''



@app.route('/chat/', methods=['GET'])
def api_all_chat11():
    results = {"success":1,"errorMessage":"","message":{"chatBotName":"CyberTy","chatBotID":63906,"message":"sup?"}}
    print(request.args['message'])
    results = {"success":1,"errorMessage":"","message":{"chatBotName":"CyberTy","chatBotID":63906,"message":"sup?"+request.args['message'],"emotion":""}}
 
    return jsonify(results)

@app.route('/api/chat/', methods=['GET'])
def api_all_chats():
    results = {"success":1,"errorMessage":"","message":{"chatBotName":"CyberTy","chatBotID":63906,"message":"sup?"}}
    print(request.args['message'])
    results = {"success":1,"errorMessage":"","message":{"chatBotName":"CyberTy","chatBotID":63906,"message":"sup?"+request.args['message'],"emotion":""}}
 
    return jsonify(results)

@app.route('/api/chat', methods=['GET'])
def api_all_chat():
    results = {"success":1,"errorMessage":"","message":{"chatBotName":"CyberTy","chatBotID":63906,"message":"sup?"}}
    print(request.args['message'])
    results = {"success":1,"errorMessage":"","message":{"chatBotName":"CyberTy","chatBotID":63906,"message":"sup?"+request.args['message'],"emotion":""}}
 
    return jsonify(results)

@app.route('/chat', methods=['GET'])
def api_all():
    results = {"success":1,"errorMessage":"","message":{"chatBotName":"CyberTy","chatBotID":63906,"message":"sup?"}}
    print(request.args['message'])
    results = {"success":1,"errorMessage":"","message":{"chatBotName":"CyberTy","chatBotID":63906,"message":"sup?"+request.args['message'],"emotion":""}}
 
    return jsonify(results)


@app.route('/chat1', methods=['GET'])
def api_id():
    # Check if an ID was provided as part of the URL.
    # If ID is provided, assign it to a variable.
    # If no ID is provided, display an error in the browser.
    print(request.args['message'])
    results = {"success":1,"errorMessage":"","message":{"chatBotName":"CyberTy","chatBotID":63906,"message":"sup?"+request.args['message'],"emotion":null}}
    # Use the jsonify function from Flask to convert our list of
    # Python dictionaries to the JSON format.
    return jsonify(results)

app.run(host="0.0.0.0")
