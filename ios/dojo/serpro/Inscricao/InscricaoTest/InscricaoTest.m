//
//  InscricaoTest.m
//  InscricaoTest
//
//  Created by Cleverson Sacramento on 18/05/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "InscricaoTest.h"
#import "Inscricao.h"


@implementation InscricaoTest

- (void)setUp
{
    [super setUp];
    
    // Set-up code here.
}

- (void)tearDown
{
    // Tear-down code here.
    
    [super tearDown];
}

- (void)testExample
{
    Inscricao *inscricao = [[Inscricao alloc] init];
    
    [inscricao cadastrar:@"olá"];
    [inscricao cadastrar:@"olá2"];
    [inscricao cadastrar:@"olá"];
    
    [inscricao release];
    
    //STFail(@"Unit tests are not implemented yet in InscricaoTest");
}

@end
