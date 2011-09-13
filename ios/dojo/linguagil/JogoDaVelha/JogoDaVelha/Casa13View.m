//
//  Casa13View.m
//  JogoDaVelha
//
//  Created by Cleverson Sacramento on 12/09/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "Casa13View.h"
#import "TabuleiroView.h"

@implementation Casa13View

-(void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event
{
    TabuleiroView *view = (TabuleiroView *)[self superview];
    
    if([view jogadaPermitidaNaLinha:1 eColuna:3]){
        self.image = [UIImage imageNamed:[view proximaMarcacao]];
        
        [view marcarLinha:1 eColuna:3];
    }
}

@end
