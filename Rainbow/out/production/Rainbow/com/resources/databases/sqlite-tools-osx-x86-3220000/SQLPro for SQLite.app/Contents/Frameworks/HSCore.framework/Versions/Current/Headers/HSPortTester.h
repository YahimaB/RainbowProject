//
//  PortTester.h
//  HSShared
//
//  Created by Kyle Hankinson on 2014-08-11.
//  Copyright (c) 2014 Hankinsoft Development, Inc. All rights reserved.
//

@import Foundation;

@interface HSPortTester : NSObject

- (id) initWithHost: (NSString*) targetHost
               port: (NSInteger) targetPort;

- (BOOL) testConnection: (NSString*__autoreleasing*) pIpAddress
                  error: (NSError*__autoreleasing*) error;

@end
