//
//  HSHostCache.h
//  HSShared
//
//  Created by Kyle Hankinson on 2016-04-05.
//  Copyright © 2016 Hankinsoft Development, Inc. All rights reserved.
//

@import Foundation;

@interface HSHostCache : NSObject

+ (nonnull HSHostCache*) sharedInstance;

- (void) cacheHostNameIfRequired: (nonnull NSString*) hostName;
- (nonnull NSString*) cachedIPAddressForHost: (nonnull NSString*) hostName;

- (BOOL) isHostCached: (nonnull NSString*) hostName;

@end
