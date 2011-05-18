//
//  AlunoDuplicadoException.m
//  Inscricao
//
//  Created by Cleverson Sacramento on 18/05/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "AlunoDuplicadoException.h"
#import "Aluno.h"


@implementation AlunoDuplicadoException

- (id) initWithAluno:(Aluno *) aAluno {
    NSString *message = [@"Aluno duplicado: " stringByAppendingString:aAluno.nome];
    self = [super initWithName:@"AlunoDuplicadoException" reason:message userInfo:nil];
    [message release];
    
    if (self) {
        aluno = aAluno;
    }
    
    return self;
}

@end
