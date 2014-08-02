require 'bundler'
Bundler.require

configure do |c|
  set :database, ENV['DATABASE_URL']
  # Turn on sessions. Makes the `session` hash available to routes and views.
  # enable :sessions
  set :root, File.dirname(__FILE__)
  set :views, Proc.new{ File.join(root, "views")}
end

class Fact < ActiveRecord::Base
end

get '/' do
  offset = rand(Fact.count+1)
  @fact = Fact.offset(offset).first
  erb :fact
end

# Use Sass with `views/style.scss` as your stylesheet
# To use, delete `public/stylehseets/style.css`
get '/stylesheets/style.css' do
  scss :style
end

get '/*.:extension' do
  halt 404 if ["ico","php","html","htm"].include? params[:extension]
end

get '/:fact' do
  if @fact = Fact.find_by(url: params[:fact])
  else
    @fact = Fact.create(:url => params[:fact], :content => params[:fact].split('-').join(" "))
  end
  erb :fact
end

#   404
#---------------------------------------

not_found do
  erb :notfound
end