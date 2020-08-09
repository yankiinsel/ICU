//
//  MovieListCell.swift
//  fo-training
//
//  Created by Yanki Insel on 13.03.2018.
//  Copyright © 2018 Fo. All rights reserved.
//

import UIKit

class MovieListCell: UITableViewCell {

    @IBOutlet weak var posterImageView: UIImageView!

    @IBOutlet weak var titleLabel: UILabel!
    override func awakeFromNib() {
        super.awakeFromNib()
        
        prepareImageView()
        prepareTitleLabel()
    }
    
    fileprivate func prepareImageView() {
        posterImageView.contentMode = .scaleAspectFit
    }
    
    fileprivate func prepareTitleLabel() {
        titleLabel.font = UIFont(name: "PTSans-Regular", size: 20)
    }

    
}
