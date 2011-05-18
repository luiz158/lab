//
//  AlunoDuplicadoException.h
//  Inscricao
//
//  Created by Cleverson Sacramento on 18/05/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Aluno.h"

@interface AlunoDuplicadoException : NSException {
    
    Aluno *aluno;
}

- (id) initWithAluno:(Aluno *) aluno;

@end
