# INTENT CONTACT

A Basic Flutter Plugin to open Contact Page of Native Platform


## Support
 * Android
 * iOS (Comming Soon)


## Example

    // Platform messages are asynchronous, so we initialize in an async method.
    Future<void> loadContactPage() async {
        //@result {"name": "XYZ", "number": "9876543210", "hasNumber" : "1"}
        
        String data;
        // Platform messages may fail, so we use a try/catch PlatformException.
        try {
            data = await IntentContact.platformContactPage;
            } on PlatformException {
            data = 'Failed to get contact.';
        }
    }
