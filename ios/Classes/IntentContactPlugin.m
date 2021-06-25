#import "IntentContactPlugin.h"
#if __has_include(<intent_contact/intent_contact-Swift.h>)
#import <intent_contact/intent_contact-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "intent_contact-Swift.h"
#endif

@implementation IntentContactPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftIntentContactPlugin registerWithRegistrar:registrar];
}
@end
