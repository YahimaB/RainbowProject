//
//  HSNetBiosHelper.h
//  HSShared
//
//  Created by Kyle Hankinson on 2016-01-06.
//  Copyright © 2016 Hankinsoft Development, Inc. All rights reserved.
//

@import Foundation;

@interface HSNetBiosHelper : NSObject

#if TARGET_OS_IPHONE

+ (void) setNetboisLookupBlock: (NSString * _Nullable(^_Nonnull)(NSString * _Nonnull hostName)) lookupBlock;

#endif

+ (nullable NSString*) IPAddressFromNetbios: (nonnull NSString*) hostName;

@end
