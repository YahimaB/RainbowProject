//
//  HSCore.h
//  HSCore
//
//  Created by Kyle Hankinson on 2016-12-01.
//  Copyright © 2016 Hankinsoft Development, Inc. All rights reserved.
//

@import Foundation;

#if TARGET_OS_IPHONE
@import UIKit;
#endif
#if !TARGET_OS_IPHONE && TARGET_OS_MAC
@import AppKit;
#endif

//! Project version number for HSCore.
FOUNDATION_EXPORT double HSCoreVersionNumber;

//! Project version string for HSCore.
FOUNDATION_EXPORT const unsigned char HSCoreVersionString[];

// In this header, you should import all the public headers of your framework using statements like #import <HSCore/PublicHeader.h>

#define NSNotFoundRange                     NSMakeRange(NSNotFound,NSNotFound)

#ifndef NSMixedState
#define NSMixedState            (-1)
#endif

#import "HSColor.h"
#import "HSImage.h"

// Third party
#import "PDKeychainBindingsController.h"
#import "LVDebounce.h"
#import "CHCSVParser.h"
#import "CSVReader.h"

// Business
#import "HSDataTypeDetector.h"
#import "HSFileAccessCheckerTypes.h"

// Logging
#import "HSLogHelper.h"

// Networking
#import "HSHostCache.h"
#import "HSNetBiosHelper.h"
#import "HSNetworkActivityHelper.h"
#import "HSPortTester.h"

// Threading
#import "HSSemaphore.h"

// General
#import "HSProgressBlock.h"
#import "MachTimer.h"
#import "HSPowerManagementHelper.h"

// Categories
#import "NSString+CharacterCounting.h"
#import "NSString+CompareToVersion.h"
#import "NSString+EmailValidation.h"
#import "NSString+FastEnumeration.h"
#import "NSString+Hankinsoft.h"
#import "NSString+IPHelper.h"
#import "NSString+IsNumber.h"
#import "NSString+TrimmingAdditions.h"
#import "NSString+Hex.h"
#import "NSString+MD5.h"
#import "NSData+Base64.h"
#import "NSData+HexDump.h"
#import "NSOrderedSet+HSCore.h"
#import "HSColor+Hex.h"
#import "NSArray+CaseInsensitiveIndexing.h"

// UI
#import "HSTableResources.h"

// iOS
#import "UIView+FindFirstResponder.h"
#import "HSHardwareKeyboardManager.h"

// XML
#import "GDataXMLNode.h"
